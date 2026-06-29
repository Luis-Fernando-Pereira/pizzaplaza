import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlavorEditPage } from './flavor-edit-page';

describe('FlavorEditPage', () => {
  let component: FlavorEditPage;
  let fixture: ComponentFixture<FlavorEditPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlavorEditPage],
      providers: [provideRouter([]), provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();

    fixture = TestBed.createComponent(FlavorEditPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
