import { Component, effect, inject, input } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';
import { ToastService } from '../../../../../shared/services/toast.service';
import { extractErrorMessage } from '../../../../../core/utils/http-error.util';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './category-form.html',
  styleUrl: './category-form.css'
})
export class CategoryFormComponent {

  private service = inject(CategoryService);
  private fb      = inject(FormBuilder);
  private router  = inject(Router);
  private toast   = inject(ToastService);

  category = input<Category | null>(null);

  form = this.fb.group({
    oid:         [''],
    description: ['', [Validators.required, Validators.maxLength(50)]]
  });

  constructor() {
    effect(() => {
      const category = this.category();
      if (!category) return;
      this.form.patchValue({ oid: category.oid, description: category.description });
    });
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload: Category = {
      oid:         this.form.value.oid ?? undefined,
      description: this.form.value.description!
    };

    const request$ = payload.oid
      ? this.service.update(payload)
      : this.service.save(payload);

    request$.subscribe({
      next: () => this.router.navigate(['/admin/categories']),
      error: (err) => this.toast.error(extractErrorMessage(err, 'Erro ao salvar categoria.'))
    });
  }
}
