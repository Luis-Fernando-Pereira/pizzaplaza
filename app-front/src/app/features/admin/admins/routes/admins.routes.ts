import { Routes } from '@angular/router';

export const ADMINS_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('../pages/admin-list-page/admin-list-page')
        .then(c => c.AdminListPage)
  },
  {
    path: 'new',
    loadComponent: () =>
      import('../pages/admin-create-page/admin-create-page')
        .then(c => c.AdminCreatePage)
  },
  {
    path: ':oid',
    loadComponent: () =>
      import('../pages/admin-edit-page/admin-edit-page')
        .then(c => c.AdminEditPage)
  }
];
