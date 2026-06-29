import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { FlavorApiService } from './flavor-api.service';
import { CreateFlavorRequest } from '../models/create-flavor-request';

describe('FlavorApiService', () => {
  let service: FlavorApiService;
  let http: HttpTestingController;

  const API = '/api/products/flavors';

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlavorApiService, provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(FlavorApiService);
    http    = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('findAll() deve fazer GET para a URL base de sabores', () => {
    service.findAll().subscribe(result => expect(Array.isArray(result)).toBe(true));

    const req = http.expectOne(API);
    expect(req.request.method).toBe('GET');
    req.flush([]);
  });

  it('findByOid() deve fazer GET para URL com oid', () => {
    service.findByOid('abc').subscribe();

    const req = http.expectOne(`${API}/abc`);
    expect(req.request.method).toBe('GET');
    req.flush({});
  });

  it('create() deve fazer POST com o corpo do sabor', () => {
    const request: CreateFlavorRequest = {
      name: 'Mussarela',
      price: 35,
      description: 'Clássico',
      categories: [],
    } as unknown as CreateFlavorRequest;

    service.create(request).subscribe();

    const req = http.expectOne(API);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(request);
    req.flush({});
  });

  it('update() deve fazer PUT para a URL base', () => {
    const request: CreateFlavorRequest = {
      name: 'Calabresa',
      price: 40,
      description: 'Defumada',
      categories: [],
    } as unknown as CreateFlavorRequest;

    service.update(request).subscribe();

    const req = http.expectOne(API);
    expect(req.request.method).toBe('PUT');
    req.flush({});
  });

  it('delete() deve fazer DELETE para URL com oid', () => {
    service.delete('xyz').subscribe();

    const req = http.expectOne(`${API}/xyz`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
