import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class APIService {
  public DOMAIN = 'http://localhost:8082';
  public ALL_USERS = '/api/users/';
  public ONE_USER = '/api/users/';
  constructor() { }  
  
}
