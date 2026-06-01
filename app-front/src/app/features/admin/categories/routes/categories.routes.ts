import { Routes } from '@angular/router';

export const CATEGORIES_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('../pages/category-list/category-list')
        .then(c => c.CategoryListPage)
  },
  {
    path: 'new',
    loadComponent: () =>
      import('../pages/category-create/category-create')
        .then(c => c.CategoryCreatePage)
  },
  {
    path: ':oid',
    loadComponent: () =>
      import('../pages/category-edit/category-edit')
        .then(c => c.CategoryEditPage)
  },
];

