import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Employee } from 'src/model/Employee';
import { EmployeeService } from 'src/service/employee.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent {
  employeeForm!: FormGroup;
  emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<EmployeeCreateComponent>,
    private employeeService: EmployeeService
  ) {
    this.initForm();
  }

  initForm() {
    this.employeeForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50), Validators.pattern(/^[a-zA-Z ]+$/)]],
      email: ['', [Validators.required, Validators.pattern(this.emailPattern)]],
      phone: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      hireDate: ['', Validators.required],
      department: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    const formControl = this.employeeForm.get(field);
    return formControl?.invalid && (formControl.dirty || formControl.touched);
  }

  submit() {
    if (this.employeeForm.valid) {
      const employeeData: Employee = this.employeeForm.value;
      this.employeeService.createEmployee(employeeData).subscribe(
        () => this.handleSuccess(),
        () => this.handleError()
      );
    } else {
      this.validateAllFormFields(this.employeeForm);
    }
  }

  handleSuccess() {
    Swal.fire({
      icon: 'success',
      title: 'Employee Added Successfully!',
      timer: 2000,
      showConfirmButton: false
    });
    this.dialogRef.close();
    setTimeout(() => location.reload(), 2000);
  }

  handleError() {
    Swal.fire({
      icon: 'error',
      title: 'Something went wrong!',
      showConfirmButton: true
    });
  }

  reset() {
    this.initForm();
  }

  cancel() {
    this.dialogRef.close();
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control) {
        if (control instanceof FormGroup) {
          this.validateAllFormFields(control);
        } else {
          control.markAsTouched({ onlySelf: true });
        }
      }
    });
  }
}