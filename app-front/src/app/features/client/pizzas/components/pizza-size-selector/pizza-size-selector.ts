import { Component, EventEmitter, Output } from '@angular/core';
import { PizzaSize } from '../../models/pizza-size.enum';
import { PizzaBuilderService } from '../../services/pizza-builder.service';

@Component({
  selector: 'app-pizza-size-selector',
  templateUrl: './pizza-size-selector.html',
  styleUrls: ['./pizza-size-selector.css']
})
export class PizzaSizeSelectorComponent {

  @Output() next = new EventEmitter<void>();

  sizes = Object.values(PizzaSize);

  constructor(private pizzaBuilder: PizzaBuilderService) {}

  select(size: PizzaSize): void {
    this.pizzaBuilder.setSize(size);
    this.next.emit();
  }

  sizeMeta(size: PizzaSize): { label: string; desc: string; slices: string } {
    const map: Record<PizzaSize, { label: string; desc: string; slices: string }> = {
      [PizzaSize.SMALL]:  { label: 'Broto',   desc: '25 cm de diâmetro', slices: '4 fatias' },
      [PizzaSize.MEDIUM]: { label: 'Média',   desc: '35 cm de diâmetro', slices: '6 fatias' },
      [PizzaSize.LARGE]:  { label: 'Grande',  desc: '45 cm de diâmetro', slices: '8 fatias' },
    };
    return map[size];
  }

  pizzaVisual(size: PizzaSize): { px: number } {
    const map: Record<PizzaSize, number> = {
      [PizzaSize.SMALL]: 72, [PizzaSize.MEDIUM]: 96, [PizzaSize.LARGE]: 120,
    };
    return { px: map[size] };
  }
}
