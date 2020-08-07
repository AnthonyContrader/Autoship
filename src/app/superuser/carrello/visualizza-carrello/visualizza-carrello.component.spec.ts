import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaCarrelloComponent } from './visualizza-carrello.component';

describe('VisualizzaCarrelloComponent', () => {
  let component: VisualizzaCarrelloComponent;
  let fixture: ComponentFixture<VisualizzaCarrelloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaCarrelloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaCarrelloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
