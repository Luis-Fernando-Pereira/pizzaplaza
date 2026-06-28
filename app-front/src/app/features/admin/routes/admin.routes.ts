import { Routes } from '@angular/router';
import { AdminLayout } from '../layout/pages/admin-layout/admin-layout';
import { authGuard } from '../../../core/guards/auth.guard';

export const ADMIN_ROUTES: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('../auth/pages/admin-login-page/admin-login-page')
        .then(c => c.AdminLoginPage)
  },
  {
    path: '',
    component: AdminLayout,
    canActivate: [authGuard],
    children: [
      {
        path: 'categories',
        loadChildren: () =>
          import('../categories/routes/categories.routes')
            .then(c => c.CATEGORIES_ROUTES)
      },
      {
        path: 'flavors',
        loadChildren: () =>
          import('../flavors/routes/flavors.routes')
            .then(c => c.FLAVOR_ROUTES)
      },
      {
        path: 'orders',
        loadChildren: () =>
          import('../orders/routes/orders.route')
            .then(c => c.ORDER_ROUTES)
      },
      {
        path: 'admins',
        loadChildren: () =>
          import('../admins/routes/admins.routes')
            .then(c => c.ADMINS_ROUTES)
      }
    ]
  }
];
