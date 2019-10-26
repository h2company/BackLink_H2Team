import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient, private auth: AuthenticationService) { }

  private setHeader(): HttpHeaders {
    return new HttpHeaders({
      Authorization : this.auth.readSession(),
      ContentType: 'application/json; charset=utf-8'
    });
  }

  public get(url: string): Observable<any> {
    return this.httpClient.get<any>(url, {
      headers: this.setHeader()
    });
  }

}
