import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorriereLayoutComponent } from './corriere-layout.component';

describe('CorriereLayoutComponent', () => {
  let component: CorriereLayoutComponent;
  let fixture: ComponentFixture<CorriereLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorriereLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorriereLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
