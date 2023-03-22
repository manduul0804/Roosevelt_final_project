import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListallusersComponent } from './listallusers.component';

describe('ListallusersComponent', () => {
  let component: ListallusersComponent;
  let fixture: ComponentFixture<ListallusersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListallusersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListallusersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
