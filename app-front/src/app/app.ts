import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AdminMenu } from './features/admin/layout/components/admin-menu/admin-menu';
import { AdminLayoutService } from './features/admin/layout/services/admin-layout.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  layout = inject(AdminLayoutService);

}
