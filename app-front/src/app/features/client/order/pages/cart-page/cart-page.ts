import { Component, inject } from '@angular/core';
import { CurrencyPipe, DecimalPipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { PizzaSize } from '../../../pizzas/models/pizza-size.enum';
import { OrderBuilderService } from '../../services/order-builder.service';
import { OrderApiService } from '../../services/order-api.service';
import { Order } from '../../models/order.model';
import { AuthService } from '../../../../../core/services/auth.service';
import { ToastService } from '../../../../../shared/services/toast.service';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [CurrencyPipe, DecimalPipe, RouterLink],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.css'
})
export class CartPage {

  orderBuilder = inject(OrderBuilderService);
  private auth     = inject(AuthService);
  private orderApi = inject(OrderApiService);
  private toast    = inject(ToastService);
  private router   = inject(Router);

  removePizza(index: number): void {
    this.orderBuilder.removePizza(index);
  }

  sizeMeta(size: string): string {
    const map: Record<string, string> = {
      [PizzaSize.SMALL]: 'Broto', [PizzaSize.MEDIUM]: 'Média', [PizzaSize.LARGE]: 'Grande',
    };
    return map[size] ?? size;
  }

  goToCheckout(): void {
    if (!this.auth.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }
    this.router.navigate(['/orders/checkout']);
  }
}
