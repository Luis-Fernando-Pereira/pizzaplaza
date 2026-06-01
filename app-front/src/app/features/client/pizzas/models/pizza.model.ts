import { PizzaSize } from './pizza-size.enum';
import { FlavorSnapshot } from './flavor-snapshot.model';

export interface Pizza {
  oid?: string;

  size: PizzaSize;

  flavors: FlavorSnapshot[];

  unitPrice: number;
}
