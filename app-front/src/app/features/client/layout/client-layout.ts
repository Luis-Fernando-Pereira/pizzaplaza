import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { OrderBuilderService } from '../order/services/order-builder.service';

@Component({
  selector: 'app-client-layout',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './client-layout.html',
  styleUrl: './client-layout.css'
})
export class ClientLayout {
  auth         = inject(AuthService);
  orderBuilder = inject(OrderBuilderService);
}
