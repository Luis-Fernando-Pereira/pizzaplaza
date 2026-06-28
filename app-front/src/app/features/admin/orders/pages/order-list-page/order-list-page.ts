import { Component, inject, OnInit, signal } from '@angular/core';
import { Order, OrderStatus } from '../../../../client/order/models/order.model';
import { OrderApiService } from '../../services/order-api-service.ts/order-api.service';
import { CurrencyPipe, DatePipe } from '@angular/common';

const STATUS_LABELS: Record<OrderStatus, string> = {
  RECEIVED:          'Recebido',
  PREPARING:         'Em preparo',
  LEFT_FOR_DELIVERY: 'Saiu para entrega',
  DELIVERED:         'Entregue',
};

const NEXT_STATUS: Partial<Record<OrderStatus, string>> = {
  RECEIVED:          'Iniciar preparo',
  PREPARING:         'Enviar para entrega',
  LEFT_FOR_DELIVERY: 'Marcar como entregue',
};

@Component({
  selector: 'app-order-list-page',
  standalone: true,
  imports: [CurrencyPipe, DatePipe],
  templateUrl: './order-list-page.html',
  styleUrl: './order-list-page.css',
})
export class OrderListPage implements OnInit {

  private api = inject(OrderApiService);

  orders  = signal<Order[]>([]);
  loading = signal(true);
  error   = signal('');
  advancing = signal<string | null>(null);

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.loading.set(true);
    this.error.set('');
    this.api.findAll().subscribe({
      next:     orders => this.orders.set(orders),
      error:    ()     => this.error.set('Erro ao carregar pedidos.'),
      complete: ()     => this.loading.set(false),
    });
  }

  advance(order: Order): void {
    if (!order.oid || this.isDelivered(order)) return;
    this.advancing.set(order.oid);
    this.api.advanceStatus(order.oid).subscribe({
      next: updated => {
        this.orders.update(list =>
          list.map(o => o.oid === updated.oid ? updated : o)
        );
      },
      error:    () => this.error.set('Erro ao avançar status do pedido.'),
      complete: () => this.advancing.set(null),
    });
  }

  statusLabel(status: OrderStatus): string {
    return STATUS_LABELS[status] ?? status;
  }

  advanceLabel(status: OrderStatus): string {
    return NEXT_STATUS[status] ?? '';
  }

  statusClass(status: OrderStatus): string {
    return `status-${status.toLowerCase().replace(/_/g, '-')}`;
  }

  isDelivered(order: Order): boolean {
    return order.status === 'DELIVERED';
  }

  isAdvancing(order: Order): boolean {
    return this.advancing() === order.oid;
  }
}
