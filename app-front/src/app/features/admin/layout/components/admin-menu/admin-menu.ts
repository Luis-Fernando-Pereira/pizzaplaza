import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AdminLayoutService } from '../../services/admin-layout.service';
import { AuthService } from '../../../../../core/services/auth.service';

@Component({
  selector: 'app-admin-menu',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './admin-menu.html',
  styleUrl: './admin-menu.css'
})
export class AdminMenu {

  layout = inject(AdminLayoutService);
  auth = inject(AuthService);

}
