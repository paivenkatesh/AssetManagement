import { TestBed } from '@angular/core/testing';

import { AssetBorrowingService } from './asset-borrowing.service';

describe('AssetBorrowingService', () => {
  let service: AssetBorrowingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssetBorrowingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
