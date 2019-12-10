import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public token: string;
  public avatar = new Subject<string>();

  constructor() { }

  readSession() : string{
    return this.token = localStorage.getItem('oauth') || null;
  }

  writeSession(token: string) {
    localStorage.setItem('oauth', token);
  }

  isLogged() : boolean {
    return !!localStorage.getItem('oauth');
  }

  
}
