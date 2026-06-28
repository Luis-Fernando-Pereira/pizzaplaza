import { Component, EventEmitter, Output } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { PizzaSize } from '../../models/pizza-size.enum';
import { PizzaBuilderService, SIZE_MULTIPLIERS } from '../../services/pizza-builder.service';

@Component({
  selector: 'app-pizza-size-selector',
  templateUrl: './pizza-size-selector.html',
  styleUrls: ['./pizza-size-selector.css'],
  imports: [DecimalPipe]
})
export class PizzaSizeSelectorComponent {

  @Output() next = new EventEmitter<void>();

  sizes = Object.values(PizzaSize);

  constructor(private pizzaBuilder: PizzaBuilderService) {}

  select(size: PizzaSize): void {
    this.pizzaBuilder.setSize(size);
    this.next.emit();
  }

  sizeMeta(size: PizzaSize): { label: string; desc: string; slices: string; multiplierText: string } {
    const map: Record<PizzaSize, { label: string; desc: string; slices: string; multiplierText: string }> = {
      [PizzaSize.SMALL]:  { label: 'Broto',  desc: '25 cm de diâmetro', slices: '4 fatias', multiplierText: '−30%' },
      [PizzaSize.MEDIUM]: { label: 'Média',   desc: '35 cm de diâmetro', slices: '6 fatias', multiplierText: 'Preço base' },
      [PizzaSize.LARGE]:  { label: 'Grande',  desc: '45 cm de diâmetro', slices: '8 fatias', multiplierText: '+40%' },
    };
    return map[size];
  }

  pizzaVisual(size: PizzaSize): { px: number } {
    const map: Record<PizzaSize, number> = {
      [PizzaSize.SMALL]: 72, [PizzaSize.MEDIUM]: 96, [PizzaSize.LARGE]: 120,
    };
    return { px: map[size] };
  }

  estimatedPrice(size: PizzaSize): number | null {
    const flavors = this.pizzaBuilder.pizza.flavors;
    if (!flavors.length) return null;
    const maxPrice = Math.max(...flavors.map(f => f.price));
    return Math.round(maxPrice * (SIZE_MULTIPLIERS[size] ?? 1.0) * 100) / 100;
  }
}
