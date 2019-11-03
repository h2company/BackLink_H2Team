import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from './authentication.service';
import { APIService } from '../util/api.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient, private oauth: AuthenticationService, private API: APIService) { }

  private setHeader(): HttpHeaders {
    return new HttpHeaders({
      Authorization : this.oauth.readSession(),
      ContentType: 'application/json; charset=utf-8'
    });
  }

  public get(path: string): Observable<any> {
    return this.httpClient.get<any>(this.toHost(path), {
      headers: this.setHeader()
    });
  }

  public post(path: string, data: any): Observable<any> {
    return this.httpClient.post<any>(this.toHost(path), data);
  }

  public post_oauth(path: string, data: any): Observable<any> {
    return this.httpClient.post<any>(this.toHost(path), data ,{
      headers: this.setHeader()
    });
  }

  public put(path: string, data: any): Observable<any> {
    return this.httpClient.put<any>(this.toHost(path), data);
  }

  public put_oauth(path: string, data: any): Observable<any> {
    return this.httpClient.put<any>(this.toHost(path), data, {
      headers: this.setHeader()
    });
  }

  private toHost(path): string {
    return `${this.API.DOMAIN}${path}`;
  }
}
