import { Routes } from '@angular/router';

import { AdminLayout } from './features/admin/layout/pages/admin-layout/admin-layout';

import {FlavorCreatePage} from './features/admin/flavors/pages/flavor-create-page/flavor-create-page';

export const routes: Routes = [

  {
    path: 'admin',
    component: AdminLayout,
    children: [
      {
        path: 'sabores/novo',
        component: FlavorCreatePage
      }

    ]
  }

];
