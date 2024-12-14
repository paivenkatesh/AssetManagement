import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetBorrowingComponent } from './asset-borrowing.component';

describe('AssetBorrowingComponent', () => {
  let component: AssetBorrowingComponent;
  let fixture: ComponentFixture<AssetBorrowingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetBorrowingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetBorrowingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
