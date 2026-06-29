import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { CategoryService } from './category.service';
import { Category } from '../models/category.model';

describe('CategoryService', () => {
  let service: CategoryService;
  let http: HttpTestingController;

  const API = '/api/products/categories';

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CategoryService, provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(CategoryService);
    http    = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('findAll() deve fazer GET para a URL correta', () => {
    const mock: Category[] = [{ oid: '1', description: 'Salgada' }];

    service.findAll().subscribe(result => {
      expect(result.length).toBe(1);
      expect(result[0].description).toBe('Salgada');
    });

    const req = http.expectOne(API);
    expect(req.request.method).toBe('GET');
    req.flush(mock);
  });

  it('findByOid() deve fazer GET para URL com oid', () => {
    const mock: Category = { oid: '42', description: 'Doce' };

    service.findByOid('42').subscribe(result => {
      expect(result.oid).toBe('42');
      expect(result.description).toBe('Doce');
    });

    const req = http.expectOne(`${API}/42`);
    expect(req.request.method).toBe('GET');
    req.flush(mock);
  });

  it('save() deve fazer POST com o corpo correto e retornar categoria criada', () => {
    const input: Category  = { description: 'Nova' };
    const output: Category = { oid: '99', description: 'Nova' };

    service.save(input).subscribe(result => {
      expect(result.oid).toBe('99');
    });

    const req = http.expectOne(API);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(input);
    req.flush(output);
  });

  it('update() deve fazer PUT com o corpo correto', () => {
    const category: Category = { oid: '1', description: 'Atualizada' };

    service.update(category).subscribe();

    const req = http.expectOne(API);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(category);
    req.flush(null);
  });

  it('delete() deve fazer DELETE para URL com oid', () => {
    service.delete('1').subscribe();

    const req = http.expectOne(`${API}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
