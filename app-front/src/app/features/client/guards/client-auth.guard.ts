import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const clientAuthGuard: CanActivateFn = () => {
  const token = localStorage.getItem('authToken');

  if (token) {
    return true;
  }

  return inject(Router).createUrlTree(['/login']);
};
