import { Routes } from '@angular/router';

export const CLIENT_ROUTES: Routes = [
  {
    path: 'pizzas',
    loadChildren: () =>
      import('../pizzas/routes/pizza.routes')
        .then(c => c.PIZZA_ROUTES)
  },
  {
    path: 'orders',
    loadChildren: () =>
      import('../order/routes/order.routes')
        .then(c => c.ORDER_ROUTES)
  },
  {
    path: '',
    loadChildren: () =>
      import('../home/routes/home.routes')
        .then(c => c.HOME_ROUTES)
  }
];

