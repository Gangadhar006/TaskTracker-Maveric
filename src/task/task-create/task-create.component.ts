import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Task } from 'src/model/Task';
import { SharedService } from 'src/service/shared.service';
import { TaskService } from 'src/service/task.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-task-create',
  templateUrl: './task-create.component.html',
  styleUrls: ['./task-create.component.css']
})
export class TaskCreateComponent {
  taskForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<TaskCreateComponent>,
    private taskService: TaskService,
    private router: Router,
    private sharedService: SharedService
  ) {
    this.taskForm = this.fb.group({
      taskName: ['', Validators.required]
    });
  }

  submit() {
    if (this.taskForm.valid) {
      const taskData: Task = this.taskForm.value;
      const empId: number = this.sharedService.getEmpId();
      
      this.taskService.createTask(empId, taskData).subscribe(
        () => this.handleSuccess(),
        () => this.handleError()
      );
    } else {
      this.validateAllFormFields(this.taskForm);
    }
  }

  reset() {
    this.taskForm.reset();
  }

  loadTasksPage(): void {
    const currentRoute = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  cancel() {
    this.dialogRef.close();
  }

  handleSuccess(): void {
    Swal.fire({
      icon: 'success',
      title: 'Task Added Successfully!',
      timer: 2000,
      showConfirmButton: false
    });
    this.dialogRef.close();
    this.loadTasksPage();
  }

  handleError(): void {
    Swal.fire({
      icon: 'error',
      title: 'Something went wrong!',
      showConfirmButton: true
    });
  }

  validateAllFormFields(formGroup: FormGroup): void {
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