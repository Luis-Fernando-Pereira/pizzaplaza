import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { AdminMenu } from '../../components/admin-menu/admin-menu';
import { AdminLayoutService } from '../../services/admin-layout.service';

@Component({
  selector: 'app-admin-layout',
  standalone: true,
  imports: [
    RouterOutlet,
    AdminMenu
  ],
  templateUrl: './admin-layout.html',
  styleUrl: './admin-layout.css'
})
export class AdminLayout {

  layout = inject(AdminLayoutService);

}
