import { Component } from '@angular/core';
import { PizzaBuilderService } from '../../services/pizza-builder.service';
import {PizzaSummaryComponent} from '../../components/pizza-summary/pizza-summary';
import {PizzaFlavorSelectorComponent} from '../../components/pizza-flavor-selector/pizza-flavor-selector';
import {PizzaSizeSelectorComponent} from '../../components/pizza-size-selector/pizza-size-selector';
import {CommonModule} from '@angular/common';
import {OrderBuilderService} from '../../../order/services/order-builder.service';
import {Router} from '@angular/router';
import {FlavorSnapshot} from '../../../order/models/flavor-snapshot.model';

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
    public pizzaBuilder: PizzaBuilderService,
    private orderBuilder: OrderBuilderService,
    private router: Router
  ) {}

  nextStep(): void {
    this.step++;
  }

  previousStep(): void {
    this.step--;
  }

  finish(): void {

    const pizza = this.pizzaBuilder.pizza;

    this.orderBuilder.addPizza({
      size: pizza.size,
      unitPrice: pizza.unitPrice,
      flavors: pizza.flavors.map(flavor => ({
        flavorOid: flavor.flavorOid,
        name: flavor.name,
        description: flavor.description,
        price: flavor.price
      })) as FlavorSnapshot[]
    });

    this.pizzaBuilder.clear();

    this.router.navigate(['/orders/cart']);
  }
}
