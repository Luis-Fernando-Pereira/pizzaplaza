import { Customer } from './customer.model';
import { PizzaOrder } from './pizza-order.model';

export interface Order {
  oid?: string;

  status: 'RECEIVED';

  pizzaList: PizzaOrder[];

  custumer: Customer;

  totalPrice: number;

}
