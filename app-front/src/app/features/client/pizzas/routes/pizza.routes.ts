import { Routes } from '@angular/router';

import { CreatePizzaPage } from '../pages/create-pizza-page/create-pizza-page';

export const PIZZA_ROUTES: Routes = [
  {
    path: 'build-pizza',
    component: CreatePizzaPage
  }
];
