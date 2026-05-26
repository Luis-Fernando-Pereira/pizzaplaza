import {Category} from '../../categories/models/category.model';

export interface CreateFlavorRequest {
  oid: string;

  name: string;

  description: string;

  price: number;

  categories: Category[];

}
