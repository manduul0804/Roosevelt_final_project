import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListmygroupsinvitedComponent } from './listmygroupsinvited.component';

describe('ListmygroupsinvitedComponent', () => {
  let component: ListmygroupsinvitedComponent;
  let fixture: ComponentFixture<ListmygroupsinvitedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListmygroupsinvitedComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListmygroupsinvitedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
