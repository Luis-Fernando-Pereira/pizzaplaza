import { Component, inject, signal } from '@angular/core';
import { AbstractControl, FormBuilder, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { switchMap } from 'rxjs';
import { AuthService } from '../../../../../core/services/auth.service';

function passwordMatch(form: AbstractControl): ValidationErrors | null {
  const pw = form.get('password')?.value;
  const cpw = form.get('confirmPassword')?.value;
  return pw === cpw ? null : { passwordMismatch: true };
}

@Component({
  selector: 'app-client-register-page',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './client-register-page.html',
  styleUrl: './client-register-page.css'
})
export class ClientRegisterPage {

  private fb = inject(FormBuilder);
  private auth = inject(AuthService);
  private http = inject(HttpClient);
  private router = inject(Router);

  loading = signal(false);
  error = signal('');

  form = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    cpf: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    confirmPassword: ['', [Validators.required]]
  }, { validators: passwordMatch });

  register(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading.set(true);
    this.error.set('');

    const { name, cpf, email, password } = this.form.value;

    this.http.post<{ oid: string }>('/api/auth/register', {
      name, cpf, email, password, userType: 'CLIENT'
    }).pipe(
      switchMap(({ oid }) =>
        this.http.post('/api/users/client', {
          oid, name, cpf, email, password, userType: 'CLIENT'
        })
      ),
      switchMap(() => this.auth.login(email!, password!))
    ).subscribe({
      next: () => this.router.navigate(['/pizzas']),
      error: (err) => {
        const msg = typeof err.error === 'string' ? err.error : 'Erro ao criar conta. Verifique os dados e tente novamente.';
        this.error.set(msg);
        this.loading.set(false);
      }
    });
  }
}
