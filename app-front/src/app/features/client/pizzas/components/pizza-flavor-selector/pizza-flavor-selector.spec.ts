import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaFlavorSelectorComponent } from './pizza-flavor-selector';

describe('PizzaFlavorSelector', () => {
  let component: PizzaFlavorSelectorComponent;
  let fixture: ComponentFixture<PizzaFlavorSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PizzaFlavorSelectorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PizzaFlavorSelectorComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
