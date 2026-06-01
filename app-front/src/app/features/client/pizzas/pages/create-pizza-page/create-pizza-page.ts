import { Component } from '@angular/core';
import { PizzaBuilderService } from '../../services/pizza-builder.service';
import {PizzaSummaryComponent} from '../../components/pizza-summary/pizza-summary';
import {PizzaFlavorSelectorComponent} from '../../components/pizza-flavor-selector/pizza-flavor-selector';
import {PizzaSizeSelectorComponent} from '../../components/pizza-size-selector/pizza-size-selector';

@Component({
  selector: 'app-create-pizza-page',
  imports: [
    PizzaSummaryComponent,
    PizzaFlavorSelectorComponent,
    PizzaSizeSelectorComponent
  ],
  templateUrl: './create-pizza-page.html'
})
export class CreatePizzaPage {

  step = 1;

  constructor(
    public pizzaBuilder: PizzaBuilderService
  ) {}

  nextStep(): void {
    this.step++;
  }

  previousStep(): void {
    this.step--;
  }

  finish(): void {

    const pizza = this.pizzaBuilder.pizza;

    console.log(pizza);

    // adicionar ao pedido
  }
}
