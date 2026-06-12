import {OrderBuilderService} from '../../services/order-builder.service';
import {OrderApiService} from '../../services/order-api.service';
import {Router} from '@angular/router';
import {Component} from '@angular/core';
import {Order} from '../../models/order.model';
import {CurrencyPipe} from '@angular/common';

@Component({
  selector: 'app-checkout-page',
  standalone: true,
  templateUrl: './checkout-page.html',
  styleUrls: ['./checkout-page.css'],
  imports: [CurrencyPipe]
})
export class CheckoutPage {

  constructor(
    public orderBuilder: OrderBuilderService,
    private orderApi: OrderApiService,
    private router: Router,

  ) {}

  finishOrder(): void {

    const request: Order = {
      status: 'RECEIVED',
      custumer: {
        userOid: 'usuario-logado',
        name: 'Fernando'
      },
      pizzaList: this.orderBuilder.pizzas,
      totalPrice: this.orderBuilder.totalPrice
    };

    this.orderApi.create(request)
      .subscribe({
        next: response => {

          this.orderBuilder.clear();

          this.router.navigate(['/']);
        }
      });

  }

}
