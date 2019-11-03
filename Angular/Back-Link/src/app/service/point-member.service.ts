import { Injectable } from '@angular/core';
import { IBaseService } from './Ibase.service';
import { PointMember } from '../model/point-member.model';
import { HttpService } from './http.service';
import { APIService } from '../util/api.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PointMemberService implements IBaseService<PointMember, string>{
  
  constructor(private http: HttpService , private API: APIService ) {}
  
  findById(id: string): Observable<PointMember> {
    return this.http.get(this.API.ALL_POINT_MEMBER + id);
  }
  findAll(): Observable<PointMember[]> {
    return this.http.get(this.API.ALL_POINT_MEMBER).pipe(
      map((data: PointMember[]) => data.map((item: PointMember) => item)),
    );
  }
  deleteById(id: string): Observable<PointMember> {
    throw new Error("Method not implemented.");
  }
  update(entity: PointMember): Observable<PointMember> {
    return this.http.get(this.API.ALL_POINT_MEMBER + entity);
  }
  save(entity: PointMember): Observable<PointMember> {
    throw new Error("Method not implemented.");
  }

 
  
}
