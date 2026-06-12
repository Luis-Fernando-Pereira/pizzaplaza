import {Component, inject, OnInit, signal} from '@angular/core';
import {Order} from '../../../../client/order/models/order.model';
import {OrderApiService} from '../../../../client/order/services/order-api.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-order-list-page',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './order-list-page.html',
  styleUrl: './order-list-page.css',
})
export class OrderListPage implements OnInit {
  private api = inject(OrderApiService);

  orders = signal<Order[]>([]);

  loading = signal(true);

  ngOnInit(): void {

    this.loadOrders();

  }

  loadOrders(): void {

    this.loading.set(true);

    this.api.findAll().subscribe({

      next: (response) => {
        this.orders.set(response);
      },
      error: (error) => {
        console.error(error);
      },
      complete: () => {
        this.loading.set(false);
      }

    });

  }
}
