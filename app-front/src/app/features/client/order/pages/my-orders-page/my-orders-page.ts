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

  orders        = signal<Order[]>([]);
  loading       = signal(true);
  error         = signal('');
  toastMsg      = signal('');
  showToast     = signal(false);
  updatedOids   = signal<Set<string>>(new Set());
  updatedLabels = signal<Map<string, string>>(new Map());

  private eventSource?: EventSource;
  private toastTimer?: ReturnType<typeof setTimeout>;
  private dismissTimers = new Map<string, ReturnType<typeof setTimeout>>();

  ngOnInit(): void {
    this.load();
    this.connectToEvents();
  }

  ngOnDestroy(): void {
    this.eventSource?.close();
    if (this.toastTimer) clearTimeout(this.toastTimer);
    this.dismissTimers.forEach(t => clearTimeout(t));
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

        this.markUpdated(event.orderOid, event.statusLabel);
        this.triggerToast(event.statusLabel);
      } catch { /* ignore parse errors */ }
    };
  }

  private markUpdated(oid: string, label: string): void {
    this.updatedOids.update(s => new Set([...s, oid]));
    this.updatedLabels.update(m => new Map([...m, [oid, label]]));

    if (this.dismissTimers.has(oid)) clearTimeout(this.dismissTimers.get(oid));

    const timer = setTimeout(() => this.dismissUpdate(oid), 60_000);
    this.dismissTimers.set(oid, timer);
  }

  dismissUpdate(oid: string): void {
    if (this.dismissTimers.has(oid)) {
      clearTimeout(this.dismissTimers.get(oid));
      this.dismissTimers.delete(oid);
    }
    this.updatedOids.update(s => { const n = new Set(s); n.delete(oid); return n; });
    this.updatedLabels.update(m => { const n = new Map(m); n.delete(oid); return n; });
  }

  private triggerToast(msg: string): void {
    if (this.toastTimer) clearTimeout(this.toastTimer);
    this.toastMsg.set(msg);
    this.showToast.set(true);
    this.toastTimer = setTimeout(() => this.showToast.set(false), 5000);
  }

  isUpdated(order: Order): boolean {
    return this.updatedOids().has(order.oid ?? '');
  }

  updatedLabel(order: Order): string {
    return this.updatedLabels().get(order.oid ?? '') ?? '';
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
