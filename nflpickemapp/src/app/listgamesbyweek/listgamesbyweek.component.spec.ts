import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListgamesbyweekComponent } from './listgamesbyweek.component';

describe('ListgamesbyweekComponent', () => {
  let component: ListgamesbyweekComponent;
  let fixture: ComponentFixture<ListgamesbyweekComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListgamesbyweekComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListgamesbyweekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
