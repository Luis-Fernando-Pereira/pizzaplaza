import { Component } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { OrderBuilderService } from '../../services/order-builder.service';

@Component({
  selector: 'app-pizza-summary',
  standalone: true,
  imports: [CurrencyPipe],
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css'
})
export class OrderSummary {

  constructor(
    public orderBuilder: OrderBuilderService
  ) {}

}
