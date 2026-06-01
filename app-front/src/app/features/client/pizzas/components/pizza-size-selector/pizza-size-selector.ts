import { Component, EventEmitter, Output } from '@angular/core';
import { PizzaSize } from '../../models/pizza-size.enum';
import { PizzaBuilderService } from '../../services/pizza-builder.service';

@Component({
  selector: 'app-pizza-size-selector',
  templateUrl: './pizza-size-selector.html',
  styleUrls: ['./pizza-size-selector.css']
})
export class PizzaSizeSelectorComponent {

  @Output()
  next = new EventEmitter<void>();

  sizes = Object.values(PizzaSize);

  constructor(
    private pizzaBuilder: PizzaBuilderService
  ) {}

  select(size: PizzaSize): void {

    this.pizzaBuilder.setSize(size);

    this.next.emit();
  }
}
