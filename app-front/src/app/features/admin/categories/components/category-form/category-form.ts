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

  category = input<Category | null>(null);

  form = this.fb.group({

    oid: [''],

    name: [
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

        name: category.name

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

      name: this.form.value.name!

    };

    const request = payload.oid
      ? this.service.update(payload)
      : this.service.save(payload);

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

  }

}
