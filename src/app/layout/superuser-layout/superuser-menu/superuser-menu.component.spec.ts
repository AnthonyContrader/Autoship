import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperuserMenuComponent } from './superuser-menu.component';

describe('SuperuserMenuComponent', () => {
  let component: SuperuserMenuComponent;
  let fixture: ComponentFixture<SuperuserMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuperuserMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperuserMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
