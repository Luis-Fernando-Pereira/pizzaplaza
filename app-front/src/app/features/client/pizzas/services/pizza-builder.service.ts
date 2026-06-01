import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { Pizza } from '../models/pizza.model';
import { PizzaSize } from '../models/pizza-size.enum';
import { FlavorSnapshot } from '../models/flavor-snapshot.model';

@Injectable({
  providedIn: 'root'
})
export class PizzaBuilderService {

  private pizzaSubject = new BehaviorSubject<Pizza>({
    size: PizzaSize.MEDIUM,
    flavors: [],
    unitPrice: 0
  });

  pizza$ = this.pizzaSubject.asObservable();

  get pizza(): Pizza {
    return this.pizzaSubject.value;
  }

  setSize(size: PizzaSize): void {
    this.pizzaSubject.next({
      ...this.pizza,
      size
    });
  }

  addFlavor(flavor: FlavorSnapshot): void {

    if (this.pizza.flavors.length >= 4) {
      return;
    }

    this.pizzaSubject.next({
      ...this.pizza,
      flavors: [...this.pizza.flavors, flavor]
    });

    this.calculatePrice();
  }

  removeFlavor(flavorOid: string): void {

    this.pizzaSubject.next({
      ...this.pizza,
      flavors: this.pizza.flavors.filter(
        f => f.flavorOid !== flavorOid
      )
    });

    this.calculatePrice();
  }

  private calculatePrice(): void {

    const flavors = this.pizza.flavors;

    if (!flavors.length) {
      return;
    }

    const highestPrice = Math.max(
      ...flavors.map(f => f.price)
    );

    this.pizzaSubject.next({
      ...this.pizza,
      unitPrice: highestPrice
    });
  }

  clear(): void {

    this.pizzaSubject.next({
      size: PizzaSize.MEDIUM,
      flavors: [],
      unitPrice: 0
    });
  }
}
