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
  public BACKLINK = "/api/backlink/getBackink/";
  public BACKLINKS = "/api/backlink/getBackinks";
  public BACKLINKS_USER = "/api/backlink/getBackinks/customer";
  public ADD_BACKLINK = "/api/backlink/createBackink";
  
  public ADD_BACKLINK_VF = "/api/backlink/verifyACPBacklink";
  public ADD_BACKLINK_CHECK_VF = "/api/backlink/checkverifyACPBacklink";

  constructor() { }  
  
}
