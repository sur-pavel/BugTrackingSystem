import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../components/entities/user';
import { Iservice } from './iservice';
import { Urls } from './urls';

@Injectable({
  providedIn: 'root'
})
export class UserService extends Urls implements Iservice<User>{

  taskUrl = `${this.baseUrl}tasks`;
  constructor(private httpClient: HttpClient) {
    super(httpClient);
    super.baseUrl;
  }
  public save(project: User) {
    return this.httpClient.post<User>(this.taskUrl, project)
  }

  public findById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.taskUrl}/${id}`);
  }

  public findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.taskUrl);
  }

  update(id: number, entity: User): Observable<User> {
    return this.httpClient.put<User>(`${this.taskUrl}/${id}`, entity);;
  }

  delete(id: number): void {
    this.httpClient.delete(`${this.taskUrl}/${id}`);
  }
}

