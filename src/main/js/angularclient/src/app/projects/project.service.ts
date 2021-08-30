import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from './project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private baseUrl = "http://localhost:8080/projects"
  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.baseUrl);
  }
  public save(project: Project){
    console.log(project);
    return this.httpClient.post<Project>(this.baseUrl, project)
  }
}
