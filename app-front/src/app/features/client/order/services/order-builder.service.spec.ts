import { TestBed } from '@angular/core/testing';
import { OrderBuilderService } from './order-builder.service';
import { PizzaOrder } from '../models/pizza-order.model';

const pizza = (price: number): PizzaOrder => ({
  size: 'MEDIUM',
  unitPrice: price,
  flavors: [],
});

describe('OrderBuilderService', () => {
  let service: OrderBuilderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderBuilderService);
    service.clear();
  });

  it('deve iniciar sem pizzas e com preço total zero', () => {
    expect(service.pizzas.length).toBe(0);
    expect(service.totalPrice).toBe(0);
  });

  it('addPizza() deve adicionar pizza ao pedido', () => {
    service.addPizza(pizza(30));
    expect(service.pizzas.length).toBe(1);
    expect(service.pizzas[0].unitPrice).toBe(30);
  });

  it('addPizza() deve permitir múltiplas pizzas', () => {
    service.addPizza(pizza(30));
    service.addPizza(pizza(50));
    expect(service.pizzas.length).toBe(2);
  });

  it('removePizza() deve remover pelo índice correto', () => {
    service.addPizza(pizza(30));
    service.addPizza(pizza(50));
    service.removePizza(0);
    expect(service.pizzas.length).toBe(1);
    expect(service.pizzas[0].unitPrice).toBe(50);
  });

  it('removePizza() no último índice deve remover a última pizza', () => {
    service.addPizza(pizza(30));
    service.addPizza(pizza(50));
    service.removePizza(1);
    expect(service.pizzas.length).toBe(1);
    expect(service.pizzas[0].unitPrice).toBe(30);
  });

  it('totalPrice deve somar os preços unitários de todas as pizzas', () => {
    service.addPizza(pizza(30));
    service.addPizza(pizza(50));
    service.addPizza(pizza(20));
    expect(service.totalPrice).toBe(100);
  });

  it('totalPrice deve ser zero após remover todas as pizzas', () => {
    service.addPizza(pizza(40));
    service.removePizza(0);
    expect(service.totalPrice).toBe(0);
  });

  it('clear() deve esvaziar o pedido', () => {
    service.addPizza(pizza(30));
    service.addPizza(pizza(50));
    service.clear();
    expect(service.pizzas.length).toBe(0);
    expect(service.totalPrice).toBe(0);
  });
});
