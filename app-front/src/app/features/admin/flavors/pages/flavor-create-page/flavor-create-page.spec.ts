import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlavorCreatePage } from './flavor-create-page';

describe('FlavorCreatePage', () => {
  let component: FlavorCreatePage;
  let fixture: ComponentFixture<FlavorCreatePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlavorCreatePage],
      providers: [provideRouter([]), provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();

    fixture = TestBed.createComponent(FlavorCreatePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
