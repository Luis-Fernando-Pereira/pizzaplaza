import { Routes } from '@angular/router';

export const FLAVOR_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('../pages/flavor-list-page/flavor-list-page')
        .then(c => c.FlavorListPage)
  },
  {
    path: 'new',
    loadComponent: () =>
      import('../pages/flavor-create-page/flavor-create-page')
        .then(c => c.FlavorCreatePage)
  },
  {
    path: ':oid',
    loadComponent: () =>
      import('../pages/flavor-edit-page/flavor-edit-page')
        .then(c => c.FlavorEditPage)
  },
];

