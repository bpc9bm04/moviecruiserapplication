import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieDailogComponent } from './movie-dailog.component';

describe('MovieDailogComponent', () => {
  let component: MovieDailogComponent;
  let fixture: ComponentFixture<MovieDailogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieDailogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieDailogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
