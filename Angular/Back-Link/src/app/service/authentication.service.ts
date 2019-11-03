import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public token: string;

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
