import { Injectable } from '@angular/core';

import { IBaseService } from './Ibase.service';
import { HttpService } from './http.service';

import { APIService } from '../util/api.service';
import { Backlink } from '../model/backlink.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class BacklinkService implements IBaseService<Backlink, string> {

    constructor(private http: HttpService, private API: APIService) { }

    findById(id: string): Observable<Backlink> {
        return this.http.get(this.API.BACKLINK + id);
    }

    findAll(): Observable<Backlink[]> {
        return this.http.get(this.API.BACKLINKS).pipe(
            map((data: Backlink[]) => data.map((item: Backlink) => item)),
        );
    }
    deleteById(id: string): Observable<Backlink> {
        throw new Error('Method not implemented.');
    }
    update(entity: Backlink): Observable<Backlink> {
        throw new Error("Method not implemented.");
    }
    save(entity: Backlink): Observable<Backlink> {
        return this.http.post_oauth(this.API.ADD_BACKLINK, entity);
    }

}  