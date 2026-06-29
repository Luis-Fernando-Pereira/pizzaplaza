import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlavorListPage } from './flavor-list-page';

describe('FlavorListPage', () => {
  let component: FlavorListPage;
  let fixture: ComponentFixture<FlavorListPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlavorListPage],
      providers: [provideRouter([]), provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();

    fixture = TestBed.createComponent(FlavorListPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
