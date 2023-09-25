import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from 'src/model/Task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl: string = 'http://localhost:8080/api/tasks';

  constructor(private http: HttpClient) { }

  createTask(empId: number, task: Task) {
    const taskUrl = `${this.baseUrl}/${empId}`;
    return this.http.post<Task>(taskUrl, task);
  }

  fetchTasks(empId: number): Observable<Task[]> {
    const taskUrl = `${this.baseUrl}/${empId}`;
    return this.http.get<Task[]>(taskUrl);
  }
}