import {
  Component,
  inject,
  OnInit,
  signal
} from '@angular/core';

import { FlavorApiService }
  from '../../services/flavor-api.service';

import { Flavor }
  from '../../models/flavor.model';

@Component({
  selector: 'app-flavor-list-page',
  standalone: true,
  templateUrl: './flavor-list-page.html',
  styleUrl: './flavor-list-page.css'
})
export class FlavorListPage
  implements OnInit {

  private api =
    inject(FlavorApiService);

  flavors = signal<Flavor[]>([]);

  loading = signal(true);

  ngOnInit(): void {

    this.loadFlavors();

  }

  loadFlavors(): void {

    this.loading.set(true);

    this.api.findAll().subscribe({

      next: (response) => {

        this.flavors.set(response);

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
