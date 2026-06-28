import { Component, inject, OnDestroy, OnInit, signal } from '@angular/core';
import { CurrencyPipe, DatePipe, DecimalPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { OrderApiService } from '../../services/order-api.service';
import { AuthService } from '../../../../../core/services/auth.service';
import { Order, OrderStatus } from '../../models/order.model';

interface OrderStatusEvent {
  orderOid:    string;
  userOid:     string;
  newStatus:   string;
  statusLabel: string;
}

@Component({
  selector: 'app-my-orders-page',
  standalone: true,
  imports: [CurrencyPipe, DatePipe, DecimalPipe, RouterLink],
  templateUrl: './my-orders-page.html',
  styleUrl: './my-orders-page.css'
})
export class MyOrdersPage implements OnInit, OnDestroy {

  private api  = inject(OrderApiService);
  private auth = inject(AuthService);

  orders   = signal<Order[]>([]);
  loading  = signal(true);
  error    = signal('');
  toastMsg = signal('');
  showToast = signal(false);

  private eventSource?: EventSource;
  private toastTimer?: ReturnType<typeof setTimeout>;

  ngOnInit(): void {
    this.load();
    this.connectToEvents();
  }

  ngOnDestroy(): void {
    this.eventSource?.close();
    if (this.toastTimer) clearTimeout(this.toastTimer);
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

  private connectToEvents(): void {
    const token = this.auth.getToken();
    if (!token) return;

    this.eventSource = new EventSource(`/api/orders/events?token=${token}`);

    this.eventSource.onmessage = (ev) => {
      try {
        const event: OrderStatusEvent = JSON.parse(ev.data);
        this.orders.update(list =>
          list.map(o => o.oid === event.orderOid
            ? { ...o, status: event.newStatus as OrderStatus }
            : o
          )
        );
        this.triggerToast(event.statusLabel);
      } catch { /* ignore parse errors */ }
    };
  }

  private triggerToast(msg: string): void {
    if (this.toastTimer) clearTimeout(this.toastTimer);
    this.toastMsg.set(msg);
    this.showToast.set(true);
    this.toastTimer = setTimeout(() => this.showToast.set(false), 5000);
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
