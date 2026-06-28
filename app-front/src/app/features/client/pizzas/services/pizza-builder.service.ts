import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { Pizza } from '../models/pizza.model';
import { PizzaSize } from '../models/pizza-size.enum';
import { FlavorSnapshot } from '../models/flavor-snapshot.model';

export const SIZE_MULTIPLIERS: Record<PizzaSize, number> = {
  [PizzaSize.SMALL]:  0.70,
  [PizzaSize.MEDIUM]: 1.00,
  [PizzaSize.LARGE]:  1.40,
};

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
    this.pizzaSubject.next({ ...this.pizza, size });
    this.calculatePrice();
  }

  addFlavor(flavor: FlavorSnapshot): void {
    if (this.pizza.flavors.length >= 4) return;

    this.pizzaSubject.next({
      ...this.pizza,
      flavors: [...this.pizza.flavors, flavor]
    });

    this.calculatePrice();
  }

  removeFlavor(flavorOid: string): void {
    this.pizzaSubject.next({
      ...this.pizza,
      flavors: this.pizza.flavors.filter(f => f.flavorOid !== flavorOid)
    });

    this.calculatePrice();
  }

  private calculatePrice(): void {
    const { flavors, size } = this.pizza;

    if (!flavors.length) {
      this.pizzaSubject.next({ ...this.pizza, unitPrice: 0 });
      return;
    }

    const maxPrice   = Math.max(...flavors.map(f => f.price));
    const multiplier = SIZE_MULTIPLIERS[size] ?? 1.0;
    const unitPrice  = Math.round(maxPrice * multiplier * 100) / 100;

    this.pizzaSubject.next({ ...this.pizza, unitPrice });
  }

  clear(): void {
    this.pizzaSubject.next({
      size: PizzaSize.MEDIUM,
      flavors: [],
      unitPrice: 0
    });
  }
}
