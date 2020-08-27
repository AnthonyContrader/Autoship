import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { MagazzinoDTO } from 'src/dto/magazzinodto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { $ } from 'protractor';
import { OggettoDTO } from 'src/dto/oggettodto';

@Injectable({
  providedIn: 'root'
})
export class MagazzinoService extends AbstractService<MagazzinoDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservice = 'autoship';
    this.entity = 'magazzinos';
  }

  insertMagazzino(magazzinoDTO : MagazzinoDTO, oggetto_id: number): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.microservice + '/api/magazzinosinsert',   {magazzino: magazzinoDTO, oggetto: oggetto_id ?  oggetto_id : null}, {headers: this.headerAuth});
  }

   updateMagazzino(magazzinoDTO : MagazzinoDTO, oggetto_id: number): Observable<MagazzinoDTO>{
    return this.http.put<MagazzinoDTO>('http://localhost:8080/' + this.microservice + '/api/magazzinosupdate',  {magazzino: magazzinoDTO, oggetto: oggetto_id ?  oggetto_id : null}, {headers: this.headerAuth});
  }

  deleteMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.put<MagazzinoDTO>('http://localhost:8080/' + this.microservice + '/api/magazzinodelete',  magazzinoDTO, {headers: this.headerAuth});
  }

  reinsertMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.put<MagazzinoDTO>('http://localhost:8080/' + this.microservice + '/api/magazzinoreinsert',  magazzinoDTO, {headers: this.headerAuth});
  }

/*  deleteMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/deletemagazzino',  magazzinoDTO);
  }

  reinsertMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/reinsertmagazzino',  magazzinoDTO);
  }

  getOggettoInCell(): Observable<MagazzinoDTO[]>{
    return this.http.get<MagazzinoDTO[]>('http://localhost:8080/' + this.type + '/getobjectincell');
  }

  getMagazzinoWithOggetto(): Observable<number[]>{
    return this.http.get<number[]>('http://localhost:8080/' + this.type + '/getmagazzinowithoggetto');
  }

  setCodice(otp: string, id_user: number, magazzino: MagazzinoDTO): Observable<MagazzinoDTO> {
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/setcodice', {otp: otp, user: id_user, magazzino: magazzino});
  }*/
}
