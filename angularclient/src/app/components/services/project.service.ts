import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../entities/project';
import { Iservice } from './iservice';
import { Urls } from './urls';

@Injectable({
  providedIn: 'root'
})
export class ProjectService extends Urls implements Iservice<Project>{

  projectUrl: string;
  constructor(private httpClient: HttpClient) {
    super(httpClient);
    this.projectUrl = `${Urls.BASE_URL}projects`;
  }

  public save(formData: FormData) {
    console.log("URL is " + this.projectUrl);    
    return this.httpClient.post<Project>(this.projectUrl, formData)
  }


  public findById(id: number): Observable<Project> {
    return this.httpClient.get<Project>(`${this.projectUrl}/${id}`);
  }

  public findAll(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.projectUrl);
  }

  update(id: number, entity: Project): Observable<Project> {
    return this.httpClient.put<Project>(`${this.projectUrl}/${id}`, entity);
  }

  delete(id: number): Observable<Project> {
    return this.httpClient.delete<Project>(`${this.projectUrl}/${id}`);
  }
}
