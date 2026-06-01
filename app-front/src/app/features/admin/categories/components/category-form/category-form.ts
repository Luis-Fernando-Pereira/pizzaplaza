import {
  Component,
  effect,
  inject,
  input
} from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './category-form.html',
  styleUrl: './category-form.css'
})
export class CategoryFormComponent {

  private service = inject(CategoryService);

  private fb = inject(FormBuilder);

  private router = inject(Router);

  category = input<Category | null>(null);

  form = this.fb.group({

    oid: [''],

    description: [
      '',
      [
        Validators.required,
        Validators.maxLength(50)
      ]
    ]

  });

  constructor() {

    effect(() => {

      const category = this.category();

      if (!category) {
        return;
      }

      this.form.patchValue({

        oid: category.oid,

        description: category.description

      });

    });

  }

  save(): void {

    if (this.form.invalid) {

      this.form.markAllAsTouched();

      return;

    }

    const payload: Category = {

      oid: this.form.value.oid ?? undefined,

      description: this.form.value.description!

    };

    if (payload.oid) {

      this.service.update(payload)
        .subscribe({

          next: () => {
            console.log('Categoria atualizada');
          },

          error: error => {
            console.error(error);
          }

        });

    } else {

      this.service.save(payload)
        .subscribe({

          next: response => {
            console.log(response);
          },

          error: error => {
            console.error(error);
          }

        });

    }

    this.router.navigate(['/admin/categories']);
  }

}
