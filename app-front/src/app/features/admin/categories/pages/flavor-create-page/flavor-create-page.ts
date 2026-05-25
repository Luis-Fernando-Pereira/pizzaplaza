import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

import { FlavorForm } from '../../components/flavor-form/flavor-form';

@Component({
  selector: 'app-flavor-create-page',
  standalone: true,
  imports: [
    RouterLink,
    FlavorForm
  ],
  templateUrl: './flavor-create-page.html',
  styleUrl: './flavor-create-page.css'
})
export class FlavorCreatePage {

}
