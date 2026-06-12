import { Injectable } from '@angular/core';
import { PizzaOrder } from '../models/pizza-order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderBuilderService {

  pizzas: PizzaOrder[] = [];

  addPizza(pizza: PizzaOrder): void {
    this.pizzas.push(pizza);
  }

  removePizza(index: number): void {
    this.pizzas.splice(index, 1);
  }

  clear(): void {
    this.pizzas = [];
  }

  get totalPrice(): number {
    return this.pizzas.reduce(
      (total, pizza) => total + pizza.unitPrice,
      0
    );
  }

}
