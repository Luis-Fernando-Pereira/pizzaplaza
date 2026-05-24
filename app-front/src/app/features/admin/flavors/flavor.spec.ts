import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Flavor } from './flavor';

describe('Flavor', () => {
  let component: Flavor;
  let fixture: ComponentFixture<Flavor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Flavor],
    }).compileComponents();

    fixture = TestBed.createComponent(Flavor);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
