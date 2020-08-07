import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestisciCarrelloComponent } from './gestisci-carrello.component';

describe('GestisciCarrelloComponent', () => {
  let component: GestisciCarrelloComponent;
  let fixture: ComponentFixture<GestisciCarrelloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestisciCarrelloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestisciCarrelloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
