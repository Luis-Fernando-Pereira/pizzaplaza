import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaSizeSelectorComponent } from './pizza-size-selector';

describe('PizzaSizeSelector', () => {
  let component: PizzaSizeSelectorComponent;
  let fixture: ComponentFixture<PizzaSizeSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PizzaSizeSelectorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PizzaSizeSelectorComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
