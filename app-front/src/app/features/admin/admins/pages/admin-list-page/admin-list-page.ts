import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AdminApiService } from '../../services/admin-api.service';
import { AdminUser } from '../../models/admin-user.model';

@Component({
  selector: 'app-admin-list-page',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './admin-list-page.html',
  styleUrl: './admin-list-page.css'
})
export class AdminListPage implements OnInit {

  private api = inject(AdminApiService);

  admins = signal<AdminUser[]>([]);
  loading = signal(true);

  ngOnInit(): void {
    this.loadAdmins();
  }

  inactivate(oid: string): void {
    const confirmed = confirm('Deseja inativar este administrador?');
    if (!confirmed) return;

    this.api.delete(oid).subscribe({
      next: () => {
        this.admins.update(list => list.filter(a => a.oid !== oid));
      },
      error: error => console.error(error)
    });
  }

  private loadAdmins(): void {
    this.loading.set(true);

    this.api.findAll().subscribe({
      next: response => this.admins.set(response),
      error: error => console.error(error),
      complete: () => this.loading.set(false)
    });
  }
}
