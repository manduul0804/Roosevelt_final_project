import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListmygroupleadersComponent } from './listmygroupleaders.component';

describe('ListmygroupleadersComponent', () => {
  let component: ListmygroupleadersComponent;
  let fixture: ComponentFixture<ListmygroupleadersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListmygroupleadersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListmygroupleadersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
