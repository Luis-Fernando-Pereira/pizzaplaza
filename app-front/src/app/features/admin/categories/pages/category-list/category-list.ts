import {Component, inject, OnInit, signal} from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category.model';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './category-list.html',
  styleUrl: './category-list.css'
})
export class CategoryListPage implements OnInit {
  private api = inject(CategoryService);

  categories = signal<Category[]>([]);

  loading = signal(true);

  ngOnInit(): void {

    this.loadCategories();

  }

  deleteCategory(oid: string): void {

    const confirmed = confirm('Deseja remover este sabor?');

    if (!confirmed) {
      return;
    }

    this.api.delete(oid).subscribe({
      next(result) {
        console.log(result);
      },
      error(error) {
        console.log(error);
      }
    })

    this.categories.update(categories =>
      categories.filter(category => category.oid !== oid)
    );

  }

  loadCategories(): void {

    this.loading.set(true);

    this.api.findAll().subscribe({

      next: (response) => {
        this.categories.set(response);
      },
      error: (error) => {
        console.error(error);
      },
      complete: () => {
        this.loading.set(false);
      }

    });

  }
}
