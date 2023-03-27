import { TestBed } from '@angular/core/testing';

import { PickemgroupService } from './pickemgroup.service';

describe('PickemgroupService', () => {
  let service: PickemgroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PickemgroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
