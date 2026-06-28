import {HttpClient} from '@angular/common/http';
import {Order} from '../models/order.model';
import {inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderApiService {

  private http = inject(HttpClient);

  private readonly API = '/api/orders';

  create(order: Order) {
    return this.http.post<Order>(
      this.API,
      order
    );
  }

  findAll(): Observable<Order[]> {
    return this.http.get<Order[]>(this.API);
  }

}
