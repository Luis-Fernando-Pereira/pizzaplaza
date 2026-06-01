import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaSummaryComponent } from './pizza-summary';

describe('PizzaSummary', () => {
  let component: PizzaSummaryComponent;
  let fixture: ComponentFixture<PizzaSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PizzaSummaryComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PizzaSummaryComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
