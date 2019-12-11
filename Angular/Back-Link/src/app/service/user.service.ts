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
  deleteById(id: string): Observable<any> {
    return this.http.delete_oauth(this.API.ALL_USERS + id);
  }
  update(entity: User): Observable<User> {
    return this.http.put_oauth(this.API.ALL_USERS, entity);
  }
  save(entity: User): Observable<User> {
    return this.http.post_oauth(this.API.ALL_USERS, entity);
  }
  login(data: any): Observable<any> {    
    return this.http.post(this.API.OAUTH_SIGNIN, data)
  }
  register(entity: User): Observable<any> {
    return this.http.post(this.API.OAUTH_SIGNUP, entity);
  }
  recover(data: any): Observable<any> {
    return this.http.put(this.API.OAUTH_RECOVER, data);
  }
  getinfo(): Observable<User>  {
    return this.http.get(this.API.ME);
  }
  avatar(data: any): Observable<User>  {
    return this.http.post_oauth(this.API.UPLOAD_AVATAR, data);
  }
}
