import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private empId!: number;

  setEmployeeData(empId: number) {
    this.empId = empId;
  }

  getEmpId() {
    return this.empId;
  }
}
