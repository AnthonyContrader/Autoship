import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperuserLayoutComponent } from './superuser-layout.component';

describe('SuperuserLayoutComponent', () => {
  let component: SuperuserLayoutComponent;
  let fixture: ComponentFixture<SuperuserLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuperuserLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperuserLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
