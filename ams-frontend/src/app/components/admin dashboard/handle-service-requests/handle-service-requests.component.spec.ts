import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandleServiceRequestsComponent } from './handle-service-requests.component';

describe('HandleServiceRequestsComponent', () => {
  let component: HandleServiceRequestsComponent;
  let fixture: ComponentFixture<HandleServiceRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HandleServiceRequestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HandleServiceRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
