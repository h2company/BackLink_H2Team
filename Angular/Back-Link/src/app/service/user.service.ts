import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBaseService } from './Ibase.service';

import { User } from '../model/user.model';
import { HttpService } from './http.service';
import { Roles } from '../model/roles.model';

@Injectable({
  providedIn: 'root'
})
export class UserService implements IBaseService<User, string> {

  private url = 'http://localhost:8082/api/users';

  constructor(private http: HttpService) {}

  findById(id: string): Observable<User> {
    throw new Error('Method not implemented.');
  }
  findAll(): Observable<User[]> {
    return this.http.get(this.url).pipe(
      map((data: User[]) => data.map((item: User) => item)),
    );
  }
  deleteById(id: string): Observable<User> {
    throw new Error('Method not implemented.');
  }
  update(entity: User): Observable<User> {
    throw new Error('Method not implemented.');
  }
  save(entity: User): Observable<User> {
    throw new Error('Method not implemented.');
  }

}
