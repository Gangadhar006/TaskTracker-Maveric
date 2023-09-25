import { NgModule } from '@angular/core';
import { CommonModule} from '@angular/common';
import { EmployeeCreateComponent } from './employee-create/employee-create.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from 'src/shared/navbar/navbar.component';



@NgModule({
  declarations: [
    EmployeeCreateComponent,
    EmployeeListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports:[
    EmployeeCreateComponent,
    EmployeeListComponent
  ]
})
export class EmployeeModule { }