import { Customer } from './customer.model';
import { PizzaOrder } from './pizza-order.model';

export type OrderStatus =
  | 'RECEIVED'
  | 'PREPARING'
  | 'LEFT_FOR_DELIVERY'
  | 'DELIVERED';

export interface Order {
  oid?: string;
  status: OrderStatus;
  pizzaList: PizzaOrder[];
  custumer: Customer;
  totalPrice: number;
  createdAt?: string;
}
