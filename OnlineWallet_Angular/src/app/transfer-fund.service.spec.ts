import { TestBed } from '@angular/core/testing';

import { TransferFundService } from './transfer-fund.service';

describe('TransferFundService', () => {
  let service: TransferFundService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransferFundService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
