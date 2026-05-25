import { Component, signal } from '@angular/core';

import { FlavorForm } from '../../components/flavor-form/flavor-form';

import { Flavor } from '../../models/flavor.model';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-flavor-edit-page',
  standalone: true,
  imports: [
    RouterLink,
    FlavorForm
  ],
  templateUrl: './flavor-edit-page.html',
  styleUrl: './flavor-edit-page.css'
})
export class FlavorEditPage {

  flavor = signal<Flavor>({

    oid: "1",

    name: 'Calabresa',

    description: 'Calabresa com cebola',

    price: 59.90,

    categories: []

  });

}
