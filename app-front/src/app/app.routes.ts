import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./features/client/routes/client.routes')
        .then(m => m.CLIENT_ROUTES)
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./features/admin/routes/admin.routes')
        .then(a => a.ADMIN_ROUTES)
  },
  {
    path: '**',
    loadComponent: () =>
      import('./shared/pages/not-found/not-found')
        .then(c => c.NotFoundComponent)
  }
];
