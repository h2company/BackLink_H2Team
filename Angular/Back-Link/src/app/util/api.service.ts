import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class APIService {
  public DOMAIN = 'http://localhost:8082';
  public ALL_USERS = '/api/users/';
  public ALL_POINT_MEMBER = '/api/point-member';
  public ALL_Logs='/api/point-member/logs'
  public ME = '/api/users/me/';
  public OAUTH_SIGNIN = "/api/auth/signin";
  public OAUTH_SIGNUP = "/api/auth/signup";
  constructor() { }  
  
}
