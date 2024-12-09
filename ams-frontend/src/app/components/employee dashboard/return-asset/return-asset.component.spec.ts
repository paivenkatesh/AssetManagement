import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnAssetComponent } from './return-asset.component';

describe('ReturnAssetComponent', () => {
  let component: ReturnAssetComponent;
  let fixture: ComponentFixture<ReturnAssetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReturnAssetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReturnAssetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
