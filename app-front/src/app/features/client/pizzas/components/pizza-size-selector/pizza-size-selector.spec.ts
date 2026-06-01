import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaSizeSelector } from './pizza-size-selector';

describe('PizzaSizeSelector', () => {
  let component: PizzaSizeSelector;
  let fixture: ComponentFixture<PizzaSizeSelector>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PizzaSizeSelector],
    }).compileComponents();

    fixture = TestBed.createComponent(PizzaSizeSelector);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
