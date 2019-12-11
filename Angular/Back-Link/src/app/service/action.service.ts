import { Injectable } from '@angular/core';

import { IBaseService } from './Ibase.service';
import { HttpService } from './http.service';

import { APIService } from '../util/api.service';
import { Action } from '../model/Action.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Respone } from '../model/respone.model';

@Injectable({
    providedIn: 'root'
})
export class ActionService implements IBaseService<Action, string> {

    constructor(private http: HttpService, private API: APIService) { }

    findById(id: string): Observable<Action> {
        return this.http.get(this.API.ACTION + id);
    }

    findAll(p?: number): Observable<Action[]> {
        let page = p ? p : 0;
        return this.http.get(this.API.ACTION + '?p=' + page).pipe(
            map((data: Action[]) => data.map((item: Action) => item)),
        );
    }

    findAllDashboard(p?: number): Observable<Action[]> {
        let page = p ? p : 0;
        return this.http.get(this.API.ACTION_DASHBOARD + '?p=' + page).pipe(
            map((data: Action[]) => data.map((item: Action) => item)),
        );
    }

    deleteById(id: string): Observable<Action> {
        throw new Error('Method not implemented.');
    }
    update(entity: Action): Observable<Action> {
        throw new Error("Method not implemented.");
    }
    save(entity: Action): Observable<Action> {
        return this.http.post_oauth(this.API.ACTION, entity);
    }
}  