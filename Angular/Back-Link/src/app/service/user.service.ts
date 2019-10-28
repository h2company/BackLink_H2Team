import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBaseService } from './Ibase.service';

import { User } from '../model/user.model';
import { HttpService } from './http.service';
import { Roles } from '../model/roles.model';
import { APIService } from '../util/api.service';

@Injectable({
  providedIn: 'root'
})
export class UserService implements IBaseService<User, string> {

  constructor(private http: HttpService, private API: APIService) {}

  findById(id: string): Observable<User> {
    return this.http.get(this.API.ALL_USERS + id);
  }

  findByIdSync(id: string) {
    return this.http.get(this.API.ALL_USERS + id).toPromise();
  }

  findAll(): Observable<User[]> {
    return this.http.get(this.API.ALL_USERS).pipe(
      map((data: User[]) => data.map((item: User) => item)),
    );
  }
  deleteById(id: string): Observable<User> {
    throw new Error('Method not implemented.');
  }
  update(entity: User): Observable<User> {
    return this.http.put(this.API.ALL_USERS + entity.id, entity);
  }
  save(entity: User): Observable<User> {
    throw new Error('Method not implemented.');
  }
  login(data: any): Observable<any> {    
    return this.http.post(this.API.OAUTH_SIGNIN, data)
  }
  register(entity: User): Observable<any> {
    return this.http.post(this.API.OAUTH_SIGNUP, entity);
  }
  getinfo(): Observable<User[]>  {
    return this.http.get(this.API.ME);
  }
}
