import { TestBed } from '@angular/core/testing';

import { ShowBalanceService } from './show-balance.service';

describe('ShowBalanceService', () => {
  let service: ShowBalanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowBalanceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
