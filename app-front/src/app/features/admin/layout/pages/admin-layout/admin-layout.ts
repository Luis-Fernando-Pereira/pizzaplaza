import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet, NavigationEnd, Router } from '@angular/router';
import { filter, map } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';
import { AdminMenu } from '../../components/admin-menu/admin-menu';
import { AdminLayoutService } from '../../services/admin-layout.service';
import { AuthService } from '../../../../../core/services/auth.service';

@Component({
  selector: 'app-admin-layout',
  standalone: true,
  imports: [RouterOutlet, RouterLink, AdminMenu],
  templateUrl: './admin-layout.html',
  styleUrl: './admin-layout.css'
})
export class AdminLayout {

  layout = inject(AdminLayoutService);
  auth   = inject(AuthService);

  private router = inject(Router);

  get initials(): string {
    const name = this.auth.currentUser()?.name ?? 'A';
    return name.split(' ').map((w: string) => w[0]).join('').slice(0, 2).toUpperCase();
  }

  private titleMap: Record<string, string> = {
    '/admin/flavors':    'Sabores',
    '/admin/categories': 'Categorias',
    '/admin/orders':     'Pedidos',
    '/admin/admins':     'Administradores',
  };

  pageTitle = toSignal(
    this.router.events.pipe(
      filter(e => e instanceof NavigationEnd),
      map((e: NavigationEnd) => {
        const base = Object.keys(this.titleMap).find(k => e.urlAfterRedirects.startsWith(k));
        return base ? this.titleMap[base] : 'Painel';
      })
    ),
    { initialValue: 'Painel' }
  );
}
