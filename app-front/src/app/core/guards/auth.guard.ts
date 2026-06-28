import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = () => {
  const auth   = inject(AuthService);
  const router = inject(Router);

  if (!auth.isLoggedIn()) {
    return router.createUrlTree(['/admin/login']);
  }

  const role = auth.userRole();
  if (role !== 'admin' && role !== 'seller') {
    return router.createUrlTree(['/admin/login']);
  }

  return true;
};
