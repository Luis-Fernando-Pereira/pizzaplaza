import { Component } from '@angular/core';
import { PizzaBuilderService } from '../../services/pizza-builder.service';
import {PizzaSummaryComponent} from '../../components/pizza-summary/pizza-summary';
import {PizzaFlavorSelectorComponent} from '../../components/pizza-flavor-selector/pizza-flavor-selector';
import {PizzaSizeSelectorComponent} from '../../components/pizza-size-selector/pizza-size-selector';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-create-pizza-page',
  standalone: true,
  imports: [
    PizzaSummaryComponent,
    PizzaFlavorSelectorComponent,
    PizzaSizeSelectorComponent,
    CommonModule
  ],
  templateUrl: './create-pizza-page.html',
  styleUrls: ['./create-pizza-page.css']
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
