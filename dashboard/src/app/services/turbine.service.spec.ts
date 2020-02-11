import { TestBed } from '@angular/core/testing';

import { TurbineService } from './turbine.service';

describe('TurbineService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TurbineService = TestBed.get(TurbineService);
    expect(service).toBeTruthy();
  });
});
