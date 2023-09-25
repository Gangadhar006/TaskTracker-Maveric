import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { TaskListComponent } from './task-list.component';
import { TaskService } from 'src/service/task.service';
import { SharedService } from 'src/service/shared.service';
import { Task } from 'src/model/Task';
import { TaskCreateComponent } from '../task-create/task-create.component';

describe('TaskListComponent', () => {
    let component: TaskListComponent;
    let fixture: ComponentFixture<TaskListComponent>;
    let mockDialog: jasmine.SpyObj<MatDialog>;
    let mockTaskService: jasmine.SpyObj<TaskService>;
    let mockSharedService: jasmine.SpyObj<SharedService>;
    let mockRouter: jasmine.SpyObj<Router>;

    const dummyTasks: Task[] = [
        { taskName: 'task 1' } as Task,
        { taskName: 'task 2' } as Task
    ];

    beforeEach(() => {
        mockDialog = jasmine.createSpyObj('MatDialog', ['open']);
        mockTaskService = jasmine.createSpyObj('TaskService', ['fetchTasks']);
        mockSharedService = jasmine.createSpyObj('SharedService', ['getEmpId']);
        mockRouter = jasmine.createSpyObj('Router', ['navigateByUrl']);

        TestBed.configureTestingModule({
            declarations: [TaskListComponent],
            providers: [
                { provide: MatDialog, useValue: mockDialog },
                { provide: TaskService, useValue: mockTaskService },
                { provide: SharedService, useValue: mockSharedService },
                { provide: Router, useValue: mockRouter },
            ],
        });

        fixture = TestBed.createComponent(TaskListComponent);
        component = fixture.componentInstance;
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should fetch tasks on ngOnInit', () => {
        const empId = 1;
        mockSharedService.getEmpId.and.returnValue(empId);
        mockTaskService.fetchTasks.and.returnValue(of(dummyTasks));

        fixture.detectChanges();
        component.ngOnInit();

        expect(component.tasks).toEqual(dummyTasks);
        expect(mockTaskService.fetchTasks).toHaveBeenCalledWith(empId);
    });

    it('should open dialog on addTask', () => {
        component.addTask();

        expect(mockDialog.open).toHaveBeenCalledWith(TaskCreateComponent);
    });

    it('should navigate to employees on showEmployee', () => {
        component.showEmployee();

        expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/employees');
    });
});