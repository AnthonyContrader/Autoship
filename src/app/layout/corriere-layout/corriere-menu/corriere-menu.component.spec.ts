import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorriereMenuComponent } from './corriere-menu.component';

describe('CorriereMenuComponent', () => {
  let component: CorriereMenuComponent;
  let fixture: ComponentFixture<CorriereMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorriereMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorriereMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
