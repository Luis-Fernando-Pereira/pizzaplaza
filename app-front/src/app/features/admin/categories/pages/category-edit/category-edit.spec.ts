import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryEditPage } from './category-edit';

describe('CategoryEditPage', () => {
  let component: CategoryEditPage;
  let fixture: ComponentFixture<CategoryEditPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryEditPage],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryEditPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
