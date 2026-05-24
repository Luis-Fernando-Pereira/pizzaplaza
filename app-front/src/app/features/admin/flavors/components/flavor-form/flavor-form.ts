import { Component, inject } from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

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

  private fb = inject(FormBuilder);

  form = this.fb.group({

    name: [
      '',
      [
        Validators.required,
        Validators.maxLength(50)
      ]
    ],

    description: [''],

    price: [
      null,
      [
        Validators.required,
        Validators.min(1)
      ]
    ]

  });

  save(): void {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    console.log(this.form.value);

  }

}
