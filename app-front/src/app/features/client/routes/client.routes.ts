import { Routes } from '@angular/router';
import { clientAuthGuard } from '../guards/client-auth.guard';

export const CLIENT_ROUTES: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('../auth/pages/client-login-page/client-login-page')
        .then(c => c.ClientLoginPage)
  },
  {
    path: 'register',
    loadComponent: () =>
      import('../auth/pages/client-register-page/client-register-page')
        .then(c => c.ClientRegisterPage)
  },
  {
    path: 'pizzas',
    loadChildren: () =>
      import('../pizzas/routes/pizza.routes')
        .then(c => c.PIZZA_ROUTES)
  },
  {
    path: 'orders',
    canActivate: [clientAuthGuard],
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

