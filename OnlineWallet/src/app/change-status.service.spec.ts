import { TestBed } from '@angular/core/testing';

import { ChangeStatusService } from './change-status.service';

describe('ChangeStatusService', () => {
  let service: ChangeStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChangeStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
