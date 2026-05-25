import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private api = 'http://localhost:8083/categories';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Category[]> {
    return this.http.get<Category[]>(this.api);
  }

  find(oid: string): Observable<Category> {
    return this.http.get<Category>(`${this.api}/${oid}`);
  }

  save(category: Category): Observable<Category> {
    return this.http.post<Category>(this.api, category);
  }

  update(category: Category): Observable<void> {
    return this.http.put<void>(this.api, category);
  }

  delete(oid: string): Observable<void> {
    return this.http.delete<void>(`${this.api}/${oid}`);
  }
}
