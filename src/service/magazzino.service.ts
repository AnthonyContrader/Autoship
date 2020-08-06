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
    this.type = 'magazzino';
  }

  insertMagazzino(magazzinoDTO : MagazzinoDTO, id_oggetto : number): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/insertmagazzino',   {magazzino: magazzinoDTO, oggetto: id_oggetto});
  }

  updateMagazzino(magazzinoDTO : MagazzinoDTO, id_oggetto : number): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/updatemagazzino',  {magazzino: magazzinoDTO, oggetto: id_oggetto});
  }

  deleteMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/deletemagazzino',  magazzinoDTO);
  }

  reinsertMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/reinsertmagazzino',  magazzinoDTO);
  }

  getOggettoInCell(): Observable<MagazzinoDTO[]>{
    return this.http.get<MagazzinoDTO[]>('http://localhost:8080/' + this.type + '/getobjectincell');
  }

  setCodice(otp: string, id_user: number, magazzino: MagazzinoDTO): Observable<MagazzinoDTO> {
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/setcodice', {otp: otp, user: id_user, magazzino: magazzino});
  }
}
