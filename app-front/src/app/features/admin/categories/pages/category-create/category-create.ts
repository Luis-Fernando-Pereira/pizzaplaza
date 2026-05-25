import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {CategoryFormComponent} from '../../components/category-form/category-form';

@Component({
  selector: 'app-category-create',
  imports: [
    RouterLink,
    CategoryFormComponent
  ],
  templateUrl: './category-create.html',
  styleUrl: './category-create.css',
})
export class CategoryCreatePage {}
