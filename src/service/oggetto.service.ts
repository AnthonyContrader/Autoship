import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { OggettoDTO } from 'src/dto/oggettodto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { Observable } from 'rxjs';

/**
 * I service sono decorati da @Injectable. 
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 * 
 * @author Vittorio Valent
 * 
 * @see AbstractService
 */
@Injectable({
  providedIn: 'root'
})
export class OggettoService extends AbstractService<OggettoDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservice= 'autoship';
    this.entity = 'oggettos';
  }

  deleteOggetto(oggettoDTO : OggettoDTO): Observable<OggettoDTO>{
    return this.http.put<OggettoDTO>('http://localhost:8080/' + this.microservice + '/api/oggettodelete',  oggettoDTO, {headers: this.headerAuth});
  }

  reinsertOggetto(OggettoDTO : OggettoDTO): Observable<OggettoDTO>{
    return this.http.put<OggettoDTO>('http://localhost:8080/' + this.microservice + '/api/oggettoreinsert',  OggettoDTO, {headers: this.headerAuth});
  }

  getOggettoNotInCell(): Observable<OggettoDTO[]>{
    return this.http.get<OggettoDTO[]>('http://localhost:8080/' + this.microservice + '/api/oggettosnotincell', {headers: this.headerAuth});
  }

  getOggettoInCell(): Observable<OggettoDTO[]>{
    return this.http.get<OggettoDTO[]>('http://localhost:8080/' + this.microservice + '/api/oggettosincell', {headers: this.headerAuth});
  }

/*  getAllObject(): Observable<OggettoDTO[]> {
    return this.http.get<OggettoDTO[]>('http://localhost:8080/' + this.type + '/getallobject')
  }

  insertOggetto(oggettoDTO : OggettoDTO): Observable<OggettoDTO>{
    return this.http.post<OggettoDTO>('http://localhost:8080/autoship/api/oggetto',  oggettoDTO);
  }

  deleteOggetto(oggettoDTO : OggettoDTO): Observable<OggettoDTO>{
    return this.http.post<OggettoDTO>('http://localhost:8080/' + this.type + '/deleteoggeto',  oggettoDTO);
  }

  reinsertOggetto(OggettoDTO : OggettoDTO): Observable<OggettoDTO>{
    return this.http.post<OggettoDTO>('http://localhost:8080/' + this.type + '/reinsertoggetto',  OggettoDTO);
  }*/
  
}
