import { Observable } from "rxjs"

export interface Iservice<T> {
    
    save(entity: T): Observable<T>;

    findById(id: number): Observable<T>;

    findAll(): Observable<T[]>;

    update(id: number, entity: T): Observable<T>;

    delete(id: number): Observable<T>;

}
