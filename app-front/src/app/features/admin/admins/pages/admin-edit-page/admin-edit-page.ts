import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { AdminApiService } from '../../services/admin-api.service';
import { AdminUser } from '../../models/admin-user.model';
import { AdminFormComponent } from '../../components/admin-form/admin-form';
import { ToastService } from '../../../../../shared/services/toast.service';
import { extractErrorMessage } from '../../../../../core/utils/http-error.util';

@Component({
  selector: 'app-admin-edit-page',
  standalone: true,
  imports: [RouterLink, AdminFormComponent],
  templateUrl: './admin-edit-page.html',
  styleUrl: './admin-edit-page.css'
})
export class AdminEditPage implements OnInit {

  private api   = inject(AdminApiService);
  private route = inject(ActivatedRoute);
  private toast = inject(ToastService);

  admin   = signal<AdminUser | null>(null);
  loading = signal(true);

  ngOnInit(): void {
    const oid = this.route.snapshot.paramMap.get('oid')!;

    this.api.findByOid(oid).subscribe({
      next:     admin => this.admin.set(admin),
      error:    (err) => this.toast.error(extractErrorMessage(err, 'Erro ao carregar administrador.')),
      complete: () => this.loading.set(false)
    });
  }
}
