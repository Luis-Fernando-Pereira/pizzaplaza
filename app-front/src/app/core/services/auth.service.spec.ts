import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

function makeJwt(payload: object): string {
  const header = btoa(JSON.stringify({ alg: 'RS256', typ: 'JWT' }));
  const body   = btoa(JSON.stringify(payload));
  return `${header}.${body}.fake-signature`;
}

function futureExp(): number {
  return Math.floor(Date.now() / 1000) + 3600;
}

function pastExp(): number {
  return Math.floor(Date.now() / 1000) - 60;
}

describe('AuthService', () => {
  let http: HttpTestingController;
  let navigateCalls: unknown[][] = [];

  const setup = (): AuthService => {
    TestBed.configureTestingModule({
      providers: [
        AuthService,
        provideHttpClient(),
        provideHttpClientTesting(),
        { provide: Router, useValue: { navigate: (args: unknown[]) => navigateCalls.push(args) } },
      ],
    });
    http = TestBed.inject(HttpTestingController);
    return TestBed.inject(AuthService);
  };

  beforeEach(() => {
    sessionStorage.clear();
    navigateCalls = [];
  });

  afterEach(() => {
    sessionStorage.clear();
    if (http) http.verify();
  });

  it('deve iniciar sem usuário quando não há token na sessão', () => {
    const service = setup();
    expect(service.isLoggedIn()).toBe(false);
    expect(service.currentUser()).toBeNull();
  });

  it('getToken() deve retornar null sem sessão ativa', () => {
    const service = setup();
    expect(service.getToken()).toBeNull();
  });

  it('initials deve retornar "?" sem usuário logado', () => {
    const service = setup();
    expect(service.initials).toBe('?');
  });

  it('userRole deve retornar null sem usuário logado', () => {
    const service = setup();
    expect(service.userRole()).toBeNull();
  });

  it('login() deve salvar token e atualizar currentUser como admin', () => {
    const service = setup();
    const token = makeJwt({
      sub: 'oid-123',
      name: 'Fernando Admin',
      upn: 'fernando@test.com',
      groups: ['admin'],
      exp: futureExp(),
    });

    service.login('fernando@test.com', 'senha123').subscribe();
    const req = http.expectOne('/api/auth/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ email: 'fernando@test.com', password: 'senha123' });
    req.flush(token);

    expect(service.isLoggedIn()).toBe(true);
    expect(service.currentUser()?.role).toBe('admin');
    expect(service.currentUser()?.name).toBe('Fernando Admin');
    expect(service.currentUser()?.email).toBe('fernando@test.com');
    expect(service.userRole()).toBe('admin');
  });

  it('login() deve reconhecer papel de client', () => {
    const service = setup();
    const token = makeJwt({
      sub: 'oid-456',
      name: 'Maria',
      upn: 'maria@test.com',
      groups: ['client'],
      exp: futureExp(),
    });

    service.login('maria@test.com', '123456').subscribe();
    http.expectOne('/api/auth/login').flush(token);

    expect(service.currentUser()?.role).toBe('client');
  });

  it('initials deve retornar as iniciais do nome do usuário', () => {
    const service = setup();
    const token = makeJwt({
      sub: 'oid-1',
      name: 'João Silva',
      upn: 'j@test.com',
      groups: ['client'],
      exp: futureExp(),
    });
    service.login('j@test.com', 'pass').subscribe();
    http.expectOne('/api/auth/login').flush(token);

    expect(service.initials).toBe('JS');
  });

  it('logout() deve limpar sessão e redirecionar admin para /admin/login', () => {
    const service = setup();
    service.currentUser.set({ oid: '1', name: 'Admin', email: 'a@b.com', role: 'admin' });

    service.logout();

    expect(service.isLoggedIn()).toBe(false);
    expect(navigateCalls).toContainEqual(['/admin/login']);
  });

  it('logout() deve redirecionar client para /login', () => {
    const service = setup();
    service.currentUser.set({ oid: '1', name: 'Cliente', email: 'c@b.com', role: 'client' });

    service.logout();

    expect(service.isLoggedIn()).toBe(false);
    expect(navigateCalls).toContainEqual(['/login']);
  });

  it('deve ignorar token expirado na inicialização e limpar o sessionStorage', () => {
    sessionStorage.setItem(
      'authToken',
      makeJwt({ sub: '1', name: 'X', upn: 'x@y.com', groups: ['admin'], exp: pastExp() })
    );
    const service = setup();

    expect(service.isLoggedIn()).toBe(false);
    expect(sessionStorage.getItem('authToken')).toBeNull();
  });

  it('deve iniciar com usuário logado se token válido já estiver no sessionStorage', () => {
    sessionStorage.setItem(
      'authToken',
      makeJwt({ sub: 'oid-1', name: 'Seller', upn: 's@t.com', groups: ['seller'], exp: futureExp() })
    );
    const service = setup();

    expect(service.isLoggedIn()).toBe(true);
    expect(service.currentUser()?.role).toBe('seller');
  });
});
