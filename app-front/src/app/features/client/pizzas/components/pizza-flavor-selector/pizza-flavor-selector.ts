import {ChangeDetectorRef, Component, NgZone, OnInit, signal} from '@angular/core';

import { FlavorApiService } from '../../../../admin/flavors/services/flavor-api.service';
import { PizzaBuilderService } from '../../services/pizza-builder.service';

import { Flavor } from '../../../../admin/flavors/models/flavor.model';
import {CommonModule, DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-pizza-flavor-selector',
  templateUrl: './pizza-flavor-selector.html',
  styleUrls: ['./pizza-flavor-selector.css'],
  standalone: true,
  imports: [
    DecimalPipe,
    CommonModule
  ]
})
export class PizzaFlavorSelectorComponent implements OnInit {

  loading = signal(false);
  flavors = signal<Flavor[]>([]);

  constructor(private flavorService: FlavorApiService, public pizzaBuilder: PizzaBuilderService) {}

  ngOnInit(): void {

    this.loading.set(true);

    this.flavorService.findAll()
      .subscribe({
        next: response => {
          this.flavors.set(response);
        },
        complete: () => {
          this.loading.set(false);
        },
        error: error => {
          this.loading.set(false);
        }
      });

  }

  addFlavor(flavor: Flavor): void {

    if (this.isSelected(flavor.oid!)) {
      return;
    }

    if (this.pizzaBuilder.pizza.flavors.length >= 4) {
      return;
    }

    this.pizzaBuilder.addFlavor({
      flavorOid: flavor.oid!,
      name: flavor.name,
      description: flavor.description,
      price: flavor.price
    });

  }

  isSelected(flavorOid: string): boolean {

    return this.pizzaBuilder.pizza.flavors
      .some(flavor => flavor.flavorOid === flavorOid);

  }

  getCategoryNames(flavor: Flavor): string {

    return flavor.categories
      ?.map(category => category.description)
      .join(', ') ?? '';

  }

}
