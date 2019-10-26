import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public token: string;

  constructor() { }

  readSession() : string{
    return this.token = localStorage.getItem('auth') || null;
  }

  writeSession(token: string) {
    localStorage.setItem('auth', token);
  }

  isLogged() : boolean {
    return !!localStorage.getItem('auth');
  }
}
