import { FlavorSnapshot } from '../../pizzas/models/flavor-snapshot.model';

export interface PizzaOrder {

  size: string;

  unitPrice: number;

  flavors: FlavorSnapshot[];

}
