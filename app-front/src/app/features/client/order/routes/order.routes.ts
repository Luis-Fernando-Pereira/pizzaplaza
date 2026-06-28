import { Routes } from '@angular/router';
import { CartPage } from '../pages/cart-page/cart-page';
import { CheckoutPage } from '../pages/checkout-page/checkout-page';

export const ORDER_ROUTES: Routes = [
  {
    path: 'cart',
    component: CartPage
  },
  {
    path: 'checkout',
    component: CheckoutPage
  },
  {
    path: 'history',
    loadComponent: () =>
      import('../pages/my-orders-page/my-orders-page')
        .then(c => c.MyOrdersPage)
  }
];
