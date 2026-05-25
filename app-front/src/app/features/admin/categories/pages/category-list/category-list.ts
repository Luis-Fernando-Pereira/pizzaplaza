import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category.model';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './category-list.html'
})
export class CategoryListPage implements OnInit {

  categories: Category[] = [];

  constructor(private service: CategoryService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.service.findAll().subscribe({
      next: data => {
        this.categories = data;
      }
    });
  }

  delete(oid: string): void {
    this.service.delete(oid).subscribe({
      next: () => this.load()
    });
  }
}
