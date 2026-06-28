import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { AdminApiService } from '../../services/admin-api.service';
import { AdminUser } from '../../models/admin-user.model';
import { AdminFormComponent } from '../../components/admin-form/admin-form';

@Component({
  selector: 'app-admin-edit-page',
  standalone: true,
  imports: [RouterLink, AdminFormComponent],
  templateUrl: './admin-edit-page.html',
  styleUrl: './admin-edit-page.css'
})
export class AdminEditPage implements OnInit {

  private api = inject(AdminApiService);
  private route = inject(ActivatedRoute);

  admin = signal<AdminUser | null>(null);

  ngOnInit(): void {
    const oid = this.route.snapshot.paramMap.get('oid')!;

    this.api.findByOid(oid).subscribe({
      next: admin => this.admin.set(admin),
      error: error => console.error(error)
    });
  }
}
