import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryCreatePage } from './category-create';

describe('CategoryCreatePage', () => {
  let component: CategoryCreatePage;
  let fixture: ComponentFixture<CategoryCreatePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryCreatePage],
      providers: [provideRouter([]), provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryCreatePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
