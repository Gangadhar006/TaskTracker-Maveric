import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from '../employee/employee-list/employee-list.component';
import { EmployeeCreateComponent } from '../employee/employee-create/employee-create.component';
import { TaskCreateComponent } from 'src/task/task-create/task-create.component';
import { TaskListComponent } from 'src/task/task-list/task-list.component';

const routes: Routes = [
  { path: 'employees', component: EmployeeListComponent },
  { path: 'tasks', component: TaskListComponent },
  { path: 'create-employee', component: EmployeeCreateComponent },
  { path: 'create-task', component: TaskCreateComponent },
  { path: '', redirectTo: '/employees', pathMatch: 'full' },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }