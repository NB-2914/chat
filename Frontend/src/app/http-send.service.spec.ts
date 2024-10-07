import { TestBed } from '@angular/core/testing';

import { HttpSendService } from './http-send.service';

describe('HttpSendService', () => {
  let service: HttpSendService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpSendService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
