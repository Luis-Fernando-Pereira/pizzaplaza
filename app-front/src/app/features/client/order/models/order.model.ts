import { Customer } from './customer.model';
import { PizzaOrder } from './pizza-order.model';

export interface Order {

  status: 'RECEIVED';

  pizzaList: PizzaOrder[];

  costumer: Customer;

  totalPrice: number;

}
