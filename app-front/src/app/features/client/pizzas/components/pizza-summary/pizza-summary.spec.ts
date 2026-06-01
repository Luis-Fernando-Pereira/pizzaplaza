import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaSummary } from './pizza-summary';

describe('PizzaSummary', () => {
  let component: PizzaSummary;
  let fixture: ComponentFixture<PizzaSummary>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PizzaSummary],
    }).compileComponents();

    fixture = TestBed.createComponent(PizzaSummary);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
