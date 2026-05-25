import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlavorListPage } from './flavor-list-page';

describe('FlavorListPage', () => {
  let component: FlavorListPage;
  let fixture: ComponentFixture<FlavorListPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlavorListPage],
    }).compileComponents();

    fixture = TestBed.createComponent(FlavorListPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
