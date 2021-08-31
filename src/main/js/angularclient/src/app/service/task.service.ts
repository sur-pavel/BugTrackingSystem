import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Iservice } from './iservice';
import { Urls } from './urls';

@Injectable({
  providedIn: 'root'
})
export class TaskService extends Urls implements Iservice<Task>{

  taskUrl = `${this.baseUrl}tasks`;
  constructor(private httpClient: HttpClient) {
    super(httpClient);
    super.baseUrl;
  }
  public save(project: Task) {
    return this.httpClient.post<Task>(this.taskUrl, project)
  }

  public findById(id: number): Observable<Task> {
    return this.httpClient.get<Task>(`${this.taskUrl}/${id}`);
  }

  public findAll(): Observable<Task[]> {
    return this.httpClient.get<Task[]>(this.taskUrl);
  }

  update(id: number, entity: Task): Observable<Task> {
    return this.httpClient.put<Task>(`${this.taskUrl}/${id}`, entity);;
  }

  delete(id: number): Observable<Task> {
    return this.httpClient.delete<Task>(`${this.taskUrl}/${id}`);
  }
}
