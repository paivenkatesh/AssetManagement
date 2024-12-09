import { TestBed } from '@angular/core/testing';

import { IssueTypeService } from './issue-type.service';

describe('IssueTypeService', () => {
  let service: IssueTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IssueTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
