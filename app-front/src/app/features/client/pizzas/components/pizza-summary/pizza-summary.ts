import { Component } from '@angular/core';

import { PizzaBuilderService } from '../../services/pizza-builder.service';
import {DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-pizza-summary',
  templateUrl: './pizza-summary.html',
  styleUrls: ['./pizza-summary.css'],
  imports: [DecimalPipe]
})
export class PizzaSummaryComponent {

  constructor(
    public pizzaBuilder: PizzaBuilderService
  ) {}

  removeFlavor(flavorOid: string): void {

    this.pizzaBuilder.removeFlavor(flavorOid);

  }

  get canFinish(): boolean {

    return this.pizzaBuilder.pizza.flavors.length > 0;

  }

}
