import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

interface Flavor {
  id: number;
  name: string;
  description: string;
  price: number;
}

@Component({
  selector: 'app-flavor-list-page',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './flavor-list-page.html',
  styleUrl: './flavor-list-page.css'
})
export class FlavorListPage {

  flavors = signal<Flavor[]>([
    {
      id: 1,
      name: 'Calabresa',
      description: 'Calabresa com cebola',
      price: 59.90
    },
    {
      id: 2,
      name: 'Frango com Catupiry',
      description: 'Frango desfiado com catupiry',
      price: 64.90
    },
    {
      id: 3,
      name: 'Portuguesa',
      description: 'Presunto, ovo e cebola',
      price: 69.90
    }
  ]);

  deleteFlavor(id: number): void {

    const confirmed = confirm('Deseja remover este sabor?');

    if (!confirmed) {
      return;
    }

    this.flavors.update(flavors =>
      flavors.filter(flavor => flavor.id !== id)
    );

  }

}
