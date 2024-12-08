import { TestBed } from '@angular/core/testing';

import { AssetAuditService } from './asset-audit.service';

describe('AssetAuditService', () => {
  let service: AssetAuditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssetAuditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
