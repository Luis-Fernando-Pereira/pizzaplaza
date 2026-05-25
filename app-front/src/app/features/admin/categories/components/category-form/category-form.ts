import { Component, Input, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './category-form.html'
})
export class CategoryFormComponent {

  private readonly fb = inject(FormBuilder);

  @Input()
  submitLabel = 'Salvar';

  form = this.fb.group({
    oid: [''],
    name: ['', Validators.required]
  });

}
