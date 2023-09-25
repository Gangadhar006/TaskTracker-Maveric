
import { EmployeeListComponent } from './employee-list.component';
import { EmployeeService } from 'src/service/employee.service';
import { Employee } from 'src/model/Employee';
import { SharedService } from 'src/service/shared.service';
import { TaskService } from 'src/service/task.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';


describe('EmployeeListComponent', () => {
  let component: EmployeeListComponent;
  let fixture: ComponentFixture<EmployeeListComponent>;
  let employeeService: EmployeeService;
  let dialog: MatDialog;
  let sharedService: SharedService;
  let taskService: TaskService;
  let employeeServiceSpy: jasmine.SpyObj<EmployeeService>;

  const matDialogRefStub = {
    close: jasmine.createSpy('close')
  };

  beforeEach(() => {
    employeeServiceSpy = jasmine.createSpyObj('EmployeeService', ['fetchEmployees']);

    TestBed.configureTestingModule({
      declarations: [EmployeeListComponent],
      imports: [MatDialogModule, RouterTestingModule, HttpClientTestingModule],
      providers: [
        EmployeeService,
        SharedService,
        TaskService,
        { provide: MatDialogRef, useValue: matDialogRefStub },
        { provide: EmployeeService, useValue: employeeServiceSpy },
        { provide: HttpClient, useClass: HttpClientModule }
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeListComponent);
    component = fixture.componentInstance;
    employeeService = TestBed.inject(EmployeeService);
    dialog = TestBed.inject(MatDialog);
    sharedService = TestBed.inject(SharedService);
    taskService = TestBed.inject(TaskService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch employees on init', fakeAsync(() => {
    const mockEmployees: Employee[] = [
      {
        id: 1,
        name: 'Test',
        email: 'test@example.com',
        phone: '1234567890',
        hireDate: new Date(),
        department: 'HR'
      },
      {
        id: 2,
        name: 'Test',
        email: 'test@example.com',
        phone: '1234567890',
        hireDate: new Date(),
        department: 'HR'
      }
    ];

    employeeServiceSpy.fetchEmployees.and.returnValue(of(mockEmployees));

    component.ngOnInit();
    tick();

    expect(component.employees).toEqual(mockEmployees);
  }));

  it('should open a dialog when adding an employee', () => {
    spyOn(dialog, 'open');
    component.addEmployee();
    expect(dialog.open).toHaveBeenCalledWith(jasmine.anything());
  });

  it('should set employee data and navigate to /tasks', () => {
    const empId = 1;
    spyOn(sharedService, 'setEmployeeData');
    spyOn(taskService, 'fetchTasks');
    const router = TestBed.inject(Router);
    spyOn(router, 'navigateByUrl');

    component.showEmployee(empId);

    expect(sharedService.setEmployeeData).toHaveBeenCalledWith(empId);
    expect(taskService.fetchTasks).toHaveBeenCalledWith(empId);
  });
});