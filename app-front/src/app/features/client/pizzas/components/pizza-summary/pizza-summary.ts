import { Component } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { PizzaBuilderService, SIZE_MULTIPLIERS } from '../../services/pizza-builder.service';
import { PizzaSize } from '../../models/pizza-size.enum';

@Component({
  selector: 'app-pizza-summary',
  templateUrl: './pizza-summary.html',
  styleUrls: ['./pizza-summary.css'],
  imports: [DecimalPipe]
})
export class PizzaSummaryComponent {

  constructor(public pizzaBuilder: PizzaBuilderService) {}

  removeFlavor(flavorOid: string): void {
    this.pizzaBuilder.removeFlavor(flavorOid);
  }

  sizeMeta(size: PizzaSize): string {
    const map: Record<PizzaSize, string> = {
      [PizzaSize.SMALL]: 'Broto', [PizzaSize.MEDIUM]: 'Média', [PizzaSize.LARGE]: 'Grande',
    };
    return map[size] ?? size;
  }

  get basePrice(): number {
    const flavors = this.pizzaBuilder.pizza.flavors;
    if (!flavors.length) return 0;
    return Math.max(...flavors.map(f => f.price));
  }

  get sizeMultiplierLabel(): string {
    const size = this.pizzaBuilder.pizza.size;
    const labels: Record<PizzaSize, string> = {
      [PizzaSize.SMALL]:  '×0,70 (Broto)',
      [PizzaSize.MEDIUM]: '×1,00 (Média)',
      [PizzaSize.LARGE]:  '×1,40 (Grande)',
    };
    return labels[size] ?? '×1,00';
  }

  get hasMultiplierEffect(): boolean {
    return this.pizzaBuilder.pizza.size !== PizzaSize.MEDIUM
      && this.pizzaBuilder.pizza.flavors.length > 0;
  }
}
