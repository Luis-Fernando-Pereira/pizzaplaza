import { TestBed } from '@angular/core/testing';
import { firstValueFrom, skip, take } from 'rxjs';
import { PizzaBuilderService } from './pizza-builder.service';
import { PizzaSize } from '../models/pizza-size.enum';
import { FlavorSnapshot } from '../models/flavor-snapshot.model';

const flavor = (oid: string, price: number): FlavorSnapshot => ({
  flavorOid: oid,
  name: 'Sabor ' + oid,
  description: 'Desc',
  price,
});

describe('PizzaBuilderService', () => {
  let service: PizzaBuilderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PizzaBuilderService);
    service.clear();
  });

  it('deve iniciar com tamanho MEDIUM, sem sabores e preço zero', () => {
    expect(service.pizza.size).toBe(PizzaSize.MEDIUM);
    expect(service.pizza.flavors.length).toBe(0);
    expect(service.pizza.unitPrice).toBe(0);
  });

  it('addFlavor() deve adicionar sabor à pizza', () => {
    service.addFlavor(flavor('1', 30));
    expect(service.pizza.flavors.length).toBe(1);
    expect(service.pizza.flavors[0].flavorOid).toBe('1');
  });

  it('addFlavor() deve limitar a 4 sabores', () => {
    for (let i = 0; i < 5; i++) service.addFlavor(flavor(String(i), 30));
    expect(service.pizza.flavors.length).toBe(4);
  });

  it('removeFlavor() deve remover sabor pelo oid', () => {
    service.addFlavor(flavor('abc', 30));
    service.addFlavor(flavor('xyz', 40));
    service.removeFlavor('abc');
    expect(service.pizza.flavors.length).toBe(1);
    expect(service.pizza.flavors[0].flavorOid).toBe('xyz');
  });

  it('preço deve ser o maior preço dos sabores com tamanho MEDIUM (×1.0)', () => {
    service.addFlavor(flavor('a', 40));
    service.addFlavor(flavor('b', 50));
    expect(service.pizza.unitPrice).toBe(50);
  });

  it('setSize(LARGE) deve aplicar multiplicador 1.4', () => {
    service.addFlavor(flavor('a', 50));
    service.setSize(PizzaSize.LARGE);
    expect(service.pizza.unitPrice).toBe(70);
  });

  it('setSize(SMALL) deve aplicar multiplicador 0.7', () => {
    service.addFlavor(flavor('a', 50));
    service.setSize(PizzaSize.SMALL);
    expect(service.pizza.unitPrice).toBe(35);
  });

  it('preço deve ser 0 sem sabores', () => {
    service.setSize(PizzaSize.LARGE);
    expect(service.pizza.unitPrice).toBe(0);
  });

  it('clear() deve resetar para o estado inicial', () => {
    service.addFlavor(flavor('a', 30));
    service.setSize(PizzaSize.LARGE);
    service.clear();
    expect(service.pizza.size).toBe(PizzaSize.MEDIUM);
    expect(service.pizza.flavors.length).toBe(0);
    expect(service.pizza.unitPrice).toBe(0);
  });

  it('pizza$ deve emitir atualização ao adicionar sabor', async () => {
    const emission = firstValueFrom(service.pizza$.pipe(skip(1), take(1)));
    service.addFlavor(flavor('f1', 25));
    const result = await emission;
    expect(result.flavors[0].flavorOid).toBe('f1');
  });
});
