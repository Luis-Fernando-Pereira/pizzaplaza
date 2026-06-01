import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaFlavorSelector } from './pizza-flavor-selector';

describe('PizzaFlavorSelector', () => {
  let component: PizzaFlavorSelector;
  let fixture: ComponentFixture<PizzaFlavorSelector>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PizzaFlavorSelector],
    }).compileComponents();

    fixture = TestBed.createComponent(PizzaFlavorSelector);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
