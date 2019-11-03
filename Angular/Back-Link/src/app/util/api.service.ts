import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class APIService {
  public DOMAIN = 'http://localhost:8082';
  //USER
  public ALL_USERS = '/api/users/';
  public ME = '/api/users/me/';
  public OAUTH_SIGNIN = "/api/auth/signin";
  public OAUTH_SIGNUP = "/api/auth/signup";
  public OAUTH_RECOVER = "/api/auth/recover";
  //BACKLINK
  public BACKLINKS = "/api/backlink/getBackinks";
  public ADD_BACKLINK = "/api/backlink/createBackink";
  constructor() { }  
  
}
