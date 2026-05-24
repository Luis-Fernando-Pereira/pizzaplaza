import { Component } from '@angular/core';
import {AdminMenu} from '../layout/components/admin-menu/admin-menu';

@Component({
  selector: 'app-flavor',
  imports: [
    AdminMenu
  ],
  templateUrl: './flavor.html',
  styleUrl: './flavor.css',
})
export class Flavor {}
