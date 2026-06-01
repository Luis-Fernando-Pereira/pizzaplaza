import {
  Component,
  effect,
  inject,
  input, signal
} from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { Flavor } from '../../models/flavor.model';
import {FlavorApiService} from '../../services/flavor-api.service';
import {CategoryService} from '../../../categories/services/category.service';
import {Category} from '../../../categories/models/category.model';
import {CreateFlavorRequest} from '../../models/create-flavor-request';
import {Router} from '@angular/router';

@Component({
  selector: 'app-flavor-form',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './flavor-form.html',
  styleUrl: './flavor-form.css'
})
export class FlavorForm {
  private api = inject(FlavorApiService);
  private categoryApi = inject(CategoryService);
  private fb = inject(FormBuilder);
  private router = inject(Router);

  flavor = input<Flavor | null>(null);
  categories = signal<Category[]>([]);

  form = this.fb.group({
    oid: [''],

    name: [
      '',
      [
        Validators.required,
        Validators.maxLength(50)
      ]
    ],
    description: [
      ''
    ],
    price: [
      null as number | null,
      [
        Validators.required,
        Validators.min(1)
      ]
    ],
    categories: [
      [] as string[],
      Validators.required
    ]

  });

  constructor() {

    this.loadCategories();

    effect(() => {

      const flavor = this.flavor();

      if (!flavor) {
        return;
      }

      this.form.patchValue({
        oid: flavor.oid,

        name: flavor.name,

        description: flavor.description,

        price: flavor.price,

        categories: flavor.categories?.map(c => c.oid!) ?? []

      });

    });

  }

  loadCategories(): void {

    this.categoryApi.findAll()
      .subscribe({

        next: response => {
          this.categories.set(response);
        },

        error: error => {
          console.error(error);
        }

      });

  }

  save(): void {

    if (this.form.invalid) {

      this.form.markAllAsTouched();

      return;

    }

    const payload: CreateFlavorRequest = {

      oid: this.form.value.oid!,

      name: this.form.value.name!,

      description: this.form.value.description!,

      price: this.form.value.price!,

      categories: this.form.value.categories!.map(oid => ({
        oid
      } as Category))

    };

    if (payload.oid) {

      this.api.update(payload)
        .subscribe({

          next: () => {
            console.log('Sabor atualizado');
          },

          error: error => {
            console.error(error);
          }

        });

    } else {

      this.api.create(payload)
        .subscribe({

          next: response => {
            console.log(response);
          },

          error: error => {
            console.error(error);
          }

        });

    }

    this.router.navigate(['/admin/flavors']).then(r => console.log('navigatorizo'));
  }

}
