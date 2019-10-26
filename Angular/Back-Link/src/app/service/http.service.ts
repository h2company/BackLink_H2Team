import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from './authentication.service';
import { APIService } from '../util/api.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient, private auth: AuthenticationService, private API: APIService) { }

  private setHeader(): HttpHeaders {
    return new HttpHeaders({
      Authorization : this.auth.readSession(),
      ContentType: 'application/json; charset=utf-8'
    });
  }

  public get(path: string): Observable<any> {
    return this.httpClient.get<any>(this.toHost(path), {
      headers: this.setHeader()
    });
  }

  private toHost(path): string {
    return `${this.API.DOMAIN}${path}`;
  }
}
