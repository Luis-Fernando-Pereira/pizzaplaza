import { Routes } from '@angular/router';

export const ORDER_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('../pages/order-list-page/order-list-page')
        .then(c => c.OrderListPage)
  }
];

