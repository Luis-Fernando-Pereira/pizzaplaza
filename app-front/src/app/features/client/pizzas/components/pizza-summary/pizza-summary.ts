import { Component } from '@angular/core';
import { PizzaBuilderService } from '../../services/pizza-builder.service';
import { PizzaSize } from '../../models/pizza-size.enum';
import { DecimalPipe } from '@angular/common';

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
}
