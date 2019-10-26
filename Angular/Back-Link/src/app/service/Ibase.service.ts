import { Observable } from 'rxjs';

export interface IBaseService<T, ID> {
  findById(id: ID): Observable<T>;
  findAll(): Observable<T[]>;
  deleteById(id: ID): Observable<T>;
  update(entity: T): Observable<T>;
  save(entity: T): Observable<T>;
}
