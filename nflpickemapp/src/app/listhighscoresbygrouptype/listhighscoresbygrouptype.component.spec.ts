import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListhighscoresbygrouptypeComponent } from './listhighscoresbygrouptype.component';

describe('ListhighscoresbygrouptypeComponent', () => {
  let component: ListhighscoresbygrouptypeComponent;
  let fixture: ComponentFixture<ListhighscoresbygrouptypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListhighscoresbygrouptypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListhighscoresbygrouptypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
