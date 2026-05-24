import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminLayoutService {

  collapsed = signal(false);

  toggleMenu(): void {
    this.collapsed.update(value => !value);
  }

}
