import { Routes } from '@angular/router';

export const CLIENT_ROUTES: Routes = [
  {
    path: 'pizzas',
    loadChildren: () =>
      import('../pizzas/routes/pizza.routes')
        .then(c => c.PIZZA_ROUTES)
  }
];

