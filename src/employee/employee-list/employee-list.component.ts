import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/service/employee.service';
import { Employee } from 'src/model/Employee';
import { SharedService } from 'src/service/shared.service';
import { TaskService } from 'src/service/task.service';
import { EmployeeCreateComponent } from '../employee-create/employee-create.component';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];

  constructor(
    private dialog: MatDialog,
    private employeeService: EmployeeService,
    private sharedService: SharedService,
    private taskService: TaskService,
    private router: Router
  ) { }

  ngOnInit() {
    this.fetchEmployees()
  }

  fetchEmployees() {
    this.employeeService.fetchEmployees().subscribe((response) => {
      this.employees = response;
    });
  }

  addEmployee(): void {
    this.dialog.open(EmployeeCreateComponent);
  }

  showEmployee(empId: number): void {
    this.sharedService.setEmployeeData(empId);
    this.taskService.fetchTasks(empId);
    this.router.navigate(['/tasks']);
  }
}