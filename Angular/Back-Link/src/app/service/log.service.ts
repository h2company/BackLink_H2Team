import { Injectable } from '@angular/core';
import { IBaseService } from './Ibase.service';
import { Logs } from '../model/logs.model';
import { HttpService } from './http.service';
import { APIService } from '../util/api.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LogService implements IBaseService<Logs , string> {

  constructor(private http: HttpService ,private API: APIService ){}

  findById(id: string): import("rxjs").Observable<Logs> {
    throw new Error("Method not implemented.");
  }
  findAll(): import("rxjs").Observable<Logs[]> {
    return this.http.get(this.API.ALL_Logs).pipe(
      map((data: Logs[]) => data.map((item: Logs) => item)),
    );
  }
  deleteById(id: string): import("rxjs").Observable<Logs> {
    throw new Error("Method not implemented.");
  }
  update(entity: Logs): import("rxjs").Observable<Logs> {
    throw new Error("Method not implemented.");
  }
  save(entity: Logs): import("rxjs").Observable<Logs> {
    throw new Error("Method not implemented.");
  }

  
}
