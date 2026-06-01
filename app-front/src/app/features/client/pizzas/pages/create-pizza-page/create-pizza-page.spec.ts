import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePizzaPage } from './create-pizza-page';

describe('CreatePizzaPage', () => {
  let component: CreatePizzaPage;
  let fixture: ComponentFixture<CreatePizzaPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePizzaPage],
    }).compileComponents();

    fixture = TestBed.createComponent(CreatePizzaPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
