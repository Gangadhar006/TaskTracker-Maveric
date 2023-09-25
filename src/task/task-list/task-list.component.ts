import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TaskService } from 'src/service/task.service';
import { Task } from 'src/model/Task';
import { SharedService } from 'src/service/shared.service';
import { TaskCreateComponent } from '../task-create/task-create.component';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  tasks: Task[] | undefined;

  constructor(
    private dialog: MatDialog,
    private taskService: TaskService,
    private sharedService: SharedService,
    private router: Router
  ) {}

  ngOnInit() {
    const empId: number = this.sharedService.getEmpId();
    if (empId) {
      this.fetchTasks(empId);
    } else {
      console.log('Something went wrong');
    }
  }

  addTask(): void {
    this.dialog.open(TaskCreateComponent);
  }

  showEmployee() {
    this.router.navigateByUrl('/employees');
  }

  fetchTasks(empId: number): void {
    localStorage.setItem('empId', `${empId}`);
    this.taskService.fetchTasks(empId).subscribe((tasks) => {
      this.tasks = tasks;
    });
  }
}