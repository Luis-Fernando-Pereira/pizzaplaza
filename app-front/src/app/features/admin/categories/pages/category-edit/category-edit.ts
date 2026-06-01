import {Component, inject, signal} from '@angular/core';
import {CategoryFormComponent} from "../../../categories/components/category-form/category-form";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {CategoryService} from '../../services/category.service';
import {Category} from '../../models/category.model';

@Component({
  selector: 'app-category-edit',
    imports: [
        CategoryFormComponent,
        RouterLink
    ],
  templateUrl: './category-edit.html',
  styleUrl: './category-edit.css',
})
export class CategoryEditPage {

  private route = inject(ActivatedRoute);

  private api = inject(CategoryService);

  category = signal<Category | null>(null);

  loading = signal(true);

  ngOnInit(): void {

    const oid = this.route.snapshot.paramMap.get('oid');

    if (!oid) {
      return;
    }

    this.api.findByOid(oid)
      .subscribe({

        next: response => {
          this.category.set(response);
        },

        error: error => {
          console.error(error);
        },

        complete: () => {
          this.loading.set(false);
        }

      });

  }
}
