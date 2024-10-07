import { TestBed } from '@angular/core/testing';

import { GetDisplayService } from './get-display.service';

describe('GetDisplayService', () => {
  let service: GetDisplayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetDisplayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
