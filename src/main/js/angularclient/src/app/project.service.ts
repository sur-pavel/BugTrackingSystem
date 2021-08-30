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

  getProjectsList(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(`${this.baseUrl}`);

  }
}
