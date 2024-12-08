import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetAuditsComponent } from './asset-audits.component';

describe('AssetAuditsComponent', () => {
  let component: AssetAuditsComponent;
  let fixture: ComponentFixture<AssetAuditsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetAuditsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetAuditsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
