import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperuserDashboardComponent } from './superuser-dashboard.component';

describe('SuperuserDashboardComponent', () => {
  let component: SuperuserDashboardComponent;
  let fixture: ComponentFixture<SuperuserDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuperuserDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperuserDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
