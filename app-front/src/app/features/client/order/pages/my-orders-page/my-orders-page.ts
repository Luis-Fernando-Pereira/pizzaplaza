import { Component, inject, OnInit, signal } from '@angular/core';
import { CurrencyPipe, DatePipe, DecimalPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { OrderApiService } from '../../services/order-api.service';
import { Order } from '../../models/order.model';

@Component({
  selector: 'app-my-orders-page',
  standalone: true,
  imports: [CurrencyPipe, DatePipe, DecimalPipe, RouterLink],
  templateUrl: './my-orders-page.html',
  styleUrl: './my-orders-page.css'
})
export class MyOrdersPage implements OnInit {

  private api = inject(OrderApiService);

  orders  = signal<Order[]>([]);
  loading = signal(true);
  error   = signal('');

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading.set(true);
    this.error.set('');
    this.api.findMyOrders().subscribe({
      next: orders => {
        this.orders.set(orders);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Não foi possível carregar seus pedidos.');
        this.loading.set(false);
      }
    });
  }

  statusLabel(status: string): string {
    const map: Record<string, string> = {
      RECEIVED:          'Recebido',
      PREPARING:         'Em preparo',
      LEFT_FOR_DELIVERY: 'Saiu para entrega',
      DELIVERED:         'Entregue',
    };
    return map[status] ?? status;
  }

  statusClass(status: string): string {
    return `status-${status.toLowerCase().replace(/_/g, '-')}`;
  }

  sizeMeta(size: string): string {
    const map: Record<string, string> = {
      SMALL:  'Broto',
      MEDIUM: 'Média',
      LARGE:  'Grande',
    };
    return map[size] ?? size;
  }
}
