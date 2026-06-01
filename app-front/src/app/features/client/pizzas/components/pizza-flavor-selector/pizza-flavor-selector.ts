import { Component, OnInit } from '@angular/core';

import { FlavorApiService } from '../../../../admin/flavors/services/flavor-api.service';
import { PizzaBuilderService } from '../../services/pizza-builder.service';

import { Flavor } from '../../../../admin/flavors/models/flavor.model';
import { DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-pizza-flavor-selector',
  templateUrl: './pizza-flavor-selector.html',
  styleUrls: ['./pizza-flavor-selector.css'],
  imports: [
    DecimalPipe
  ]
})
export class PizzaFlavorSelectorComponent implements OnInit {

  flavors: Flavor[] = [];

  loading = false;

  constructor(private flavorService: FlavorApiService, public pizzaBuilder: PizzaBuilderService) {}

  ngOnInit(): void {

    this.loading = true;

    this.flavorService.findAll()
      .subscribe({
        next: response => {
          this.flavors = response;
        },
        complete: () => {
          this.loading = false;
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
