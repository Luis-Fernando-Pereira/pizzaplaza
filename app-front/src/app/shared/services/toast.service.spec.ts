import { TestBed } from '@angular/core/testing';
import { ToastService } from './toast.service';

describe('ToastService', () => {
  let service: ToastService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ToastService);
  });

  it('show() deve adicionar toast à lista', () => {
    service.show('Mensagem de teste', 'info');
    expect(service.toasts().length).toBe(1);
    expect(service.toasts()[0].message).toBe('Mensagem de teste');
    expect(service.toasts()[0].type).toBe('info');
  });

  it('dismiss() deve remover toast pelo id', () => {
    service.show('Toast A', 'info');
    const id = service.toasts()[0].id;
    service.dismiss(id);
    expect(service.toasts().length).toBe(0);
  });

  it('dismiss() não deve remover outro toast', () => {
    service.show('A', 'info');
    service.show('B', 'info');
    const idA = service.toasts()[0].id;
    service.dismiss(idA);
    expect(service.toasts().length).toBe(1);
    expect(service.toasts()[0].message).toBe('B');
  });

  it('success() deve adicionar toast do tipo success', () => {
    service.success('Operação realizada!');
    expect(service.toasts()[0].type).toBe('success');
    expect(service.toasts()[0].message).toBe('Operação realizada!');
  });

  it('error() deve adicionar toast do tipo error', () => {
    service.error('Algo deu errado.');
    expect(service.toasts()[0].type).toBe('error');
  });

  it('info() deve adicionar toast do tipo info', () => {
    service.info('Informação.');
    expect(service.toasts()[0].type).toBe('info');
  });

  it('warning() deve adicionar toast do tipo warning', () => {
    service.warning('Atenção!');
    expect(service.toasts()[0].type).toBe('warning');
  });

  it('deve acumular múltiplos toasts', () => {
    service.show('A', 'info');
    service.show('B', 'success');
    service.show('C', 'error');
    expect(service.toasts().length).toBe(3);
  });

  it('cada toast deve ter id único', () => {
    service.show('X', 'info');
    service.show('Y', 'info');
    const [a, b] = service.toasts();
    expect(a.id).not.toBe(b.id);
  });
});
