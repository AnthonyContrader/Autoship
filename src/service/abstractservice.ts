import { Service } from './service';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

/**
 * Service astratto, implementa tutti i metodi CRUD inviando request al server di SpringBoot. 
 * @param port il port del backend
 * @param type la mappatura del controller di ciascuna entità.
 * 
 * @see Service
 * 
 * @author Vittorio Valent
 */
export abstract class AbstractService<DTO> implements Service<DTO> {

    microservice: string;
    entity: string;
    authorization: string = localStorage.getItem('currentAuth');
    headerAuth = new HttpHeaders({
        'Authorization': this.authorization
      });

    constructor(protected http: HttpClient) {
    }

    getAll(): Observable<DTO[]> {
        console.log(this.authorization);
        return this.http.get<DTO[]>('http://localhost:8080/' + this.microservice + '/api/' + this.entity, {headers: this.headerAuth});
    }

    read(id: number): Observable<DTO> {
        return this.http.get<DTO>('http://localhost:8080/${this.microservice}/api/${this.entity}/${id}', {headers: this.headerAuth});
    }

    delete(id: number): Observable<any> {
        return this.http.delete('http://localhost:8080/${this.microservice}/api/${this.entity}/${id}',  {headers: this.headerAuth});
    }

    insert(dto: DTO): Observable<any> {
        return this.http.post('http://localhost:8080/' + this.microservice + '/api/' + this.entity, dto, {headers: this.headerAuth});
    }

    update(dto: DTO): Observable<DTO> {
        return this.http.put<DTO>('http://localhost:8080/' + this.microservice + '/api/' + this.entity, dto, {headers: this.headerAuth});

    }

}