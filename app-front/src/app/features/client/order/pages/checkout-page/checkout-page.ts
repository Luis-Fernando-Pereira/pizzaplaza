import { Component, inject, signal } from '@angular/core';
import { CurrencyPipe, DecimalPipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { OrderBuilderService } from '../../services/order-builder.service';
import { OrderApiService } from '../../services/order-api.service';
import { Order } from '../../models/order.model';
import { AuthService } from '../../../../../core/services/auth.service';
import { ToastService } from '../../../../../shared/services/toast.service';
import { PizzaSize } from '../../../pizzas/models/pizza-size.enum';

@Component({
  selector: 'app-checkout-page',
  standalone: true,
  templateUrl: './checkout-page.html',
  styleUrls: ['./checkout-page.css'],
  imports: [CurrencyPipe, DecimalPipe, RouterLink]
})
export class CheckoutPage {

  private auth    = inject(AuthService);
  private toast   = inject(ToastService);
  private orderApi = inject(OrderApiService);
  private router   = inject(Router);

  orderBuilder = inject(OrderBuilderService);
  user = this.auth.getUserFromToken();
  loading = false;

  sizeMeta(size: string): string {
    const map: Record<string, string> = {
      [PizzaSize.SMALL]: 'Broto', [PizzaSize.MEDIUM]: 'Média', [PizzaSize.LARGE]: 'Grande',
    };
    return map[size] ?? size;
  }

  finishOrder(): void {
    if (this.loading) return;
    this.loading = true;

    const request: Order = {
      status: 'RECEIVED',
      custumer: {
        userOid: this.user?.oid ?? '',
        name: this.user?.name ?? ''
      },
      pizzaList: this.orderBuilder.pizzas,
      totalPrice: this.orderBuilder.totalPrice
    };

    this.orderApi.create(request).subscribe({
      next: () => {
        this.orderBuilder.clear();
        this.toast.success('Pedido realizado com sucesso!');
        this.router.navigate(['/']);
      },
      error: () => {
        this.toast.error('Erro ao criar pedido. Tente novamente.');
        this.loading = false;
      }
    });
  }
}
