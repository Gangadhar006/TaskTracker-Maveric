import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EmployeeService } from './employee.service';
import { Employee } from 'src/model/Employee';

describe('EmployeeService', () => {
    let service: EmployeeService;
    let httpTestingController: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [EmployeeService]
        });
        service = TestBed.inject(EmployeeService);
        httpTestingController = TestBed.inject(HttpTestingController);
    });

    afterEach(() => {
        httpTestingController.verify();
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should create an employee', () => {
        const newEmployee = {
            id: 1,
            name: 'John Doe',
            email: 'john@example.com',
            phone: '1234567890',
            hireDate: new Date(),
            department: 'HR',
        };

        service.createEmployee(newEmployee).subscribe(employee => {
            expect(employee).toEqual(newEmployee);
        });

        const req = httpTestingController.expectOne('http://localhost:8080/api/employees');
        expect(req.request.method).toEqual('POST');
        req.flush(newEmployee);
    });

    it('should fetch employees', () => {
        const mockEmployees: Employee[] = [
            {
                id: 1,
                name: 'test',
                email: 'test@example.com',
                phone: '1234567890',
                hireDate: new Date(),
                department: 'HR',
            },
            {
                id: 2,
                name: 'John',
                email: 'john@example.com',
                phone: '1294567890',
                hireDate: new Date(),
                department: 'HR',
            }
        ];

        service.fetchEmployees().subscribe(employees => {
            expect(employees).toEqual(mockEmployees);
        });

        const req = httpTestingController.expectOne('http://localhost:8080/api/employees');
        expect(req.request.method).toEqual('GET');
        req.flush(mockEmployees);
    });
});
