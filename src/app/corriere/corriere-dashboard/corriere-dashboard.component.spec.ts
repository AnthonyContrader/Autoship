import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorriereDashboardComponent } from './corriere-dashboard.component';

describe('CorriereDashboardComponent', () => {
  let component: CorriereDashboardComponent;
  let fixture: ComponentFixture<CorriereDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorriereDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorriereDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
