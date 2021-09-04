import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../entities/user';
import { Iservice } from './iservice';
import { Urls } from './urls';

@Injectable({
  providedIn: 'root'
})
export class UserService extends Urls implements Iservice<User>{

  userUrl: string;
  constructor(private httpClient: HttpClient) {
    super(httpClient);
    this.userUrl = `${Urls.BASE_URL}users`;
  }
  public save(formData: FormData) {
    return this.httpClient.post<User>(this.userUrl, formData)
  }

  public findById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.userUrl}/${id}`);
  }

  public findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.userUrl);
  }

  update(id: number, entity: User): Observable<User> {
    return this.httpClient.put<User>(`${this.userUrl}/${id}`, entity);
  }

  delete(id: number): Observable<User> {
    return this.httpClient.delete<User>(`${this.userUrl}/${id}`);

  }
}

