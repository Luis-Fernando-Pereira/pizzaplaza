import {Category} from '../../categories/models/category.model';

export interface Flavor {

  oid?: string;

  name: string;

  description: string;

  price: number;

  categories: Category[];

}
