import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlavorForm } from './flavor-form';

describe('FlavorForm', () => {
  let component: FlavorForm;
  let fixture: ComponentFixture<FlavorForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlavorForm],
    }).compileComponents();

    fixture = TestBed.createComponent(FlavorForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
