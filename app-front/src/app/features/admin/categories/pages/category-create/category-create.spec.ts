import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryCreatePage } from './category-create';

describe('CategoryCreatePage', () => {
  let component: CategoryCreatePage;
  let fixture: ComponentFixture<CategoryCreatePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryCreatePage],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryCreatePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
