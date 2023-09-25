import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaskService } from './task.service';
import { Task } from 'src/model/Task';

describe('TaskService', () => {
    let taskService: TaskService;
    let httpTestingController: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [TaskService]
        });

        taskService = TestBed.inject(TaskService);
        httpTestingController = TestBed.inject(HttpTestingController);
    });

    afterEach(() => {
        httpTestingController.verify();
    });

    it('should be created', () => {
        expect(taskService).toBeTruthy();
    });

    it('should send a POST request to create a task', () => {
        const empId = 1;
        const task: Task = { id: 1, taskName: 'Task 1', time: new Date(), status: 'in progress' };

        taskService.createTask(empId, task).subscribe(result => {
            expect(result).toEqual(task);
        });

        const req = httpTestingController.expectOne(`http://localhost:8080/api/tasks/${empId}`);
        expect(req.request.method).toBe('POST');
        req.flush(task);
    });

    it('should send a GET request to fetch tasks', () => {
        const empId = 1;
        const mockTasks: Task[] = [
            { id: 1, taskName: 'Task 1', time: new Date(), status: 'in progress' },
            { id: 2, taskName: 'Task 2', time: new Date(), status: 'in progress' }
        ];

        taskService.fetchTasks(empId).subscribe(tasks => {
            expect(tasks).toEqual(mockTasks);
        });

        const req = httpTestingController.expectOne(`http://localhost:8080/api/tasks/${empId}`);
        expect(req.request.method).toBe('GET');
        req.flush(mockTasks);
    });
});