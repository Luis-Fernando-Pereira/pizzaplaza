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

import { Flavor } from '../../models/flavor.model';
import {FlavorApiService} from '../../services/flavor-api.service';

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

  private fb = inject(FormBuilder);

  flavor = input<Flavor | null>(null);

  form = this.fb.group({

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
    ]

  });

  constructor() {

    effect(() => {

      const flavor = this.flavor();

      if (!flavor) {
        return;
      }

      this.form.patchValue({

        name: flavor.name,

        description: flavor.description,

        price: flavor.price

      });

    });

  }

  save(): void {

    if (this.form.invalid) {

      this.form.markAllAsTouched();

      return;

    }

    const payload = {

      name: this.form.value.name!,
      description: this.form.value.description!,
      price: this.form.value.price!

    };

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

}
