import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../../../../client/order/models/order.model';

@Injectable({ providedIn: 'root' })
export class OrderApiService {

  private http = inject(HttpClient);
  private readonly API = '/api/orders';

  findAll(): Observable<Order[]> {
    return this.http.get<Order[]>(this.API);
  }

  advanceStatus(oid: string): Observable<Order> {
    return this.http.patch<Order>(`${this.API}/${oid}/advance-status`, null);
  }
}
