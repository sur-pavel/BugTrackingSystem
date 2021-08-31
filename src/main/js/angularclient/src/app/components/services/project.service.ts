import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, SubscribableOrPromise } from 'rxjs';
import { Project } from '../entities/project';
import { Iservice } from './iservice';
import { Urls } from './urls';

@Injectable({
  providedIn: 'root'
})
export class ProjectService extends Urls implements Iservice<Project>{

  projectUrl = `${this.baseUrl}projects`;
  constructor(private httpClient: HttpClient) {
    super(httpClient);
    super.baseUrl;
  }

  public save(project: Project) {
    return this.httpClient.post<Project>(this.projectUrl, project)
  }

  public findById(id: number): Observable<Project> {
    return this.httpClient.get<Project>(`${this.projectUrl}/${id}`);
  }

  public findAll(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.projectUrl);
  }

  update(id: number, entity: Project): Observable<Project> {
    return this.httpClient.put<Project>(`${this.projectUrl}/${id}`, entity);;
  }

  delete(id: number):Observable<Project> {
    return this.httpClient.delete<Project>(`${this.projectUrl}/${id}`);
  }
}
