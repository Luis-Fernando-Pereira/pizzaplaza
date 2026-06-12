import { Routes } from '@angular/router';
import {AdminLayout} from '../layout/pages/admin-layout/admin-layout';

export const ADMIN_ROUTES: Routes = [
  {
    path: '',
    component: AdminLayout,
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
      }
    ]
  }

];

