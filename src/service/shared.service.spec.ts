import { TestBed } from '@angular/core/testing';
import { SharedService } from './shared.service';

describe('SharedService', () => {
  let service: SharedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SharedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should set and get empId', () => {
    const empId = 123;
    service.setEmployeeData(empId);
    expect(service.getEmpId()).toEqual(empId);
  });
});