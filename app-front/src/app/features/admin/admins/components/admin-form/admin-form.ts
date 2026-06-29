import { Component, computed, effect, inject, input } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AdminApiService } from '../../services/admin-api.service';
import { AdminUser } from '../../models/admin-user.model';
import { ToastService } from '../../../../../shared/services/toast.service';
import { extractErrorMessage } from '../../../../../core/utils/http-error.util';

@Component({
  selector: 'app-admin-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './admin-form.html',
  styleUrl: './admin-form.css'
})
export class AdminFormComponent {

  private service = inject(AdminApiService);
  private fb      = inject(FormBuilder);
  private router  = inject(Router);
  private toast   = inject(ToastService);

  admin = input<AdminUser | null>(null);

  readonly isEdit = computed(() => !!this.admin()?.oid);

  form = this.fb.group({
    oid:      [''],
    name:     ['', [Validators.required, Validators.maxLength(100)]],
    cpf:      ['', [Validators.required]],
    email:    ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]]
  });

  constructor() {
    effect(() => {
      const admin = this.admin();
      if (!admin) return;

      this.form.patchValue({ oid: admin.oid, name: admin.name, cpf: admin.cpf, email: admin.email });

      const pwCtrl = this.form.controls.password;
      pwCtrl.clearValidators();
      pwCtrl.addValidators(Validators.minLength(8));
      pwCtrl.updateValueAndValidity();
    });
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { oid, name, cpf, email, password } = this.form.value;

    const payload: AdminUser = {
      oid:      oid ?? undefined,
      name:     name!,
      cpf:      cpf!,
      email:    email!,
      password: password?.trim() || undefined,
      userType: 'ADMIN'
    };

    const request$: Observable<unknown> = payload.oid
      ? this.service.update(payload)
      : this.service.create(payload);

    request$.subscribe({
      next:  () => this.router.navigate(['/admin/admins']),
      error: (err: HttpErrorResponse) => this.toast.error(extractErrorMessage(err, 'Erro ao salvar administrador.'))
    });
  }
}
