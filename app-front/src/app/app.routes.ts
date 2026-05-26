import { Routes } from '@angular/router';

import { AdminLayout } from './features/admin/layout/pages/admin-layout/admin-layout';

import {FlavorCreatePage} from './features/admin/flavors/pages/flavor-create-page/flavor-create-page';
import {FlavorListPage} from './features/admin/flavors/pages/flavor-list-page/flavor-list-page';
import {FlavorEditPage} from './features/admin/flavors/pages/flavor-edit-page/flavor-edit-page';
import {CategoryListPage} from './features/admin/categories/pages/category-list/category-list';
import {CategoryCreatePage} from './features/admin/categories/pages/category-create/category-create';
import {CategoryEditPage} from './features/admin/categories/pages/category-edit/category-edit';

export const routes: Routes = [
  {
    path: 'admin',
    component: AdminLayout,
    children: [
      {
        path: 'sabores/novo',
        component: FlavorCreatePage
      },
      {
        path: 'sabores',
        component: FlavorListPage
      },
      {
        path: 'sabores/:oid/editar',
        component: FlavorEditPage
      },
      {
        path: 'categorias',
        component: CategoryListPage
      },
      {
        path: 'categorias/novo',
        component: CategoryCreatePage
      },
      {
        path: 'categories/:oid/editar',
        component: CategoryEditPage
      }
    ]
  }

];
