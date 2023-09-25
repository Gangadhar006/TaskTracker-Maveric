import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { EmployeeCreateComponent } from './employee-create.component';
import { EmployeeService } from 'src/service/employee.service';

describe('EmployeeCreateComponent', () => {
    let component: EmployeeCreateComponent;
    let fixture: ComponentFixture<EmployeeCreateComponent>;
    let dialogRefMock: MatDialogRef<EmployeeCreateComponent>;
    let employeeServiceMock: EmployeeService;

    beforeEach(async(() => {
        dialogRefMock = jasmine.createSpyObj('MatDialogRef', ['close']);
        employeeServiceMock = jasmine.createSpyObj('EmployeeService', ['createEmployee']);
        TestBed.configureTestingModule({
            declarations: [EmployeeCreateComponent],
            imports: [ReactiveFormsModule],
            providers: [
                FormBuilder,
                { provide: MatDialogRef, useValue: dialogRefMock },
                { provide: EmployeeService, useValue: employeeServiceMock },
            ],
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(EmployeeCreateComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should initialize the form', () => {
        expect(component.employeeForm).toBeTruthy();
    });

    it('should mark form fields as invalid when empty', () => {
        const nameControl = component.employeeForm.get('name');
        if (nameControl) {
            nameControl.markAsTouched();
            nameControl.markAsDirty();
            expect(component.isFieldInvalid('name')).toBeTruthy();
        } else {
            fail('nameControl should not be null');
        }
    });

    it('should handle success after employee creation', () => {
        spyOn(window, 'setTimeout');
        component.handleSuccess();
        expect(dialogRefMock.close).toHaveBeenCalled();
        expect(window.setTimeout).toHaveBeenCalled();
    });

    it('should reset the form', () => {
        component.employeeForm.setValue({
            name: 'John Doe',
            email: 'john@example.com',
            phone: '1234567890',
            hireDate: new Date(),
            department: 'HR',
        });
        component.reset();
        expect(component.employeeForm.value).toEqual({
            name: '',
            email: '',
            phone: '',
            hireDate: '',
            department: '',
        });
    });

    it('should close the dialog when canceled', () => {
        component.cancel();
        expect(dialogRefMock.close).toHaveBeenCalled();
    });
});