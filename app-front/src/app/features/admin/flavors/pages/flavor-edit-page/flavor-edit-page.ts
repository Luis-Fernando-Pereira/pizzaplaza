import {
  Component,
  inject,
  OnInit,
  signal
} from '@angular/core';

import {ActivatedRoute, RouterLink} from '@angular/router';

import { FlavorApiService } from '../../services/flavor-api.service';

import { Flavor } from '../../models/flavor.model';

import { FlavorForm } from '../../components/flavor-form/flavor-form';

@Component({
  selector: 'app-flavor-edit-page',
  standalone: true,
  imports: [
    FlavorForm,
    RouterLink
  ],
  templateUrl: './flavor-edit-page.html',
  styleUrl: './flavor-edit-page.css'
})
export class FlavorEditPage implements OnInit {

  private route = inject(ActivatedRoute);

  private api = inject(FlavorApiService);

  flavor = signal<Flavor | null>(null);

  loading = signal(true);

  ngOnInit(): void {

    const oid = this.route.snapshot.paramMap.get('oid');

    if (!oid) {
      return;
    }

    this.api.findByOid(oid)
      .subscribe({

        next: response => {
          this.flavor.set(response);
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
