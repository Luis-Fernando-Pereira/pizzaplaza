import { Component } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

import { OrderBuilderService } from '../../services/order-builder.service';
import { OrderApiService } from '../../services/order-api.service';
import { Order } from '../../models/order.model';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [
    CurrencyPipe,
    RouterLink
  ],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.css'
})
export class CartPage {

  constructor(
    public orderBuilder: OrderBuilderService,
    private orderApi: OrderApiService,
    private router: Router
  ) {}

  removePizza(index: number): void {
    this.orderBuilder.removePizza(index);
  }

  finishOrder(): void {

    if (this.orderBuilder.pizzas.length === 0) {
      return;
    }

    const request: Order = {

      status: 'RECEIVED',

      custumer: {
        userOid: 'USER_OID',
        name: 'USER_NAME',
        cpf: 'USER_CPF',
      },

      pizzaList: this.orderBuilder.pizzas,

      totalPrice: this.orderBuilder.totalPrice

    };

    this.orderApi
      .create(request)
      .subscribe({

        next: response => {

          console.log('Pedido criado:', response);

          this.orderBuilder.clear();

          this.router.navigate(['/']);

        },

        error: error => {

          console.error(
            'Erro ao criar pedido',
            error
          );

        }

      });

  }

}
