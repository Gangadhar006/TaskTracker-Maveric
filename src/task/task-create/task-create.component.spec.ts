import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { SharedService } from 'src/service/shared.service';
import { TaskService } from 'src/service/task.service';
import { TaskCreateComponent } from './task-create.component';

describe('TaskCreateComponent', () => {
  let component: TaskCreateComponent;
  let fixture: ComponentFixture<TaskCreateComponent>;
  let taskService: TaskService;
  let sharedService: SharedService;
  let router: Router;
  let dialogRef: MatDialogRef<TaskCreateComponent>;

  const mockTaskService = {
    createTask: jasmine.createSpy('createTask').and.returnValue(of({})),
  };

  const mockSharedService = {
    getEmpId: jasmine.createSpy('getEmpId').and.returnValue(1),
  };

  const mockRouter = {
    url: '/',
    navigate: jasmine.createSpy('navigate'),
  };

  const mockDialogRef = {
    close: jasmine.createSpy('close'),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TaskCreateComponent],
      imports: [
        ReactiveFormsModule,
        MatDialogModule,
      ],
      providers: [
        { provide: TaskService, useValue: mockTaskService },
        { provide: SharedService, useValue: mockSharedService },
        { provide: Router, useValue: mockRouter },
        { provide: MatDialogRef, useValue: mockDialogRef },
        { provide: ActivatedRoute, useValue: { snapshot: { data: {} } } },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskCreateComponent);
    component = fixture.componentInstance;
    taskService = TestBed.inject(TaskService);
    sharedService = TestBed.inject(SharedService);
    router = TestBed.inject(Router);
    dialogRef = TestBed.inject(MatDialogRef);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should reset the form', () => {
    component.taskForm.setValue({ taskName: 'Sample Task' });
    component.reset();
    expect(component.taskForm.value.taskName).toBe(null);
  });

  it('should not submit the form when invalid', () => {
    component.submit();
    expect(mockTaskService.createTask).not.toHaveBeenCalled();
  });


  it('should validate all form fields', () => {
    component.validateAllFormFields(component.taskForm);

    const taskNameControl = component.taskForm.get('taskName');
    expect(taskNameControl).toBeTruthy('taskName control should exist');

    if (taskNameControl) {
      expect(taskNameControl.hasError('required')).toBeTruthy('taskName control should be required');
    }
  });
});