import { FlavorSnapshot } from './flavor-snapshot.model';

export interface PizzaOrder {

  size: string;

  unitPrice: number;

  flavors: FlavorSnapshot[];

}
