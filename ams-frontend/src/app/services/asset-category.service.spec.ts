import { TestBed } from '@angular/core/testing';

import { AssetCategoryService } from './asset-category.service';

describe('AssetCategoryService', () => {
  let service: AssetCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssetCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
