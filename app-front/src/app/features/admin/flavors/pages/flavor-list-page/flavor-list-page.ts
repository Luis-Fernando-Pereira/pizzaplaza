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
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-flavor-list-page',
  standalone: true,
  templateUrl: './flavor-list-page.html',
  imports: [
    RouterLink
  ],
  styleUrl: './flavor-list-page.css'
})
export class FlavorListPage implements OnInit {

  private api = inject(FlavorApiService);

  flavors = signal<Flavor[]>([]);

  loading = signal(true);

  ngOnInit(): void {

    this.loadFlavors();

  }

  deleteFlavor(oid: string): void {

    const confirmed = confirm('Deseja remover este sabor?');

    if (!confirmed) {
      return;
    }

    this.api.delete(oid).subscribe({

      next: response => {
        console.log(response);
      },

      error: error => {
        console.error(error);
      }

    });

    this.flavors.update(flavors =>
      flavors.filter(flavor => flavor.oid !== oid)
    );

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
