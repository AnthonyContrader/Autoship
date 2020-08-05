import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { MagazzinoDTO } from 'src/dto/magazzinodto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { $ } from 'protractor';

@Injectable({
  providedIn: 'root'
})
export class MagazzinoService extends AbstractService<MagazzinoDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.type = 'magazzino';
  }

  insertMagazzino(magazzinoDTO : MagazzinoDTO, id_oggetto : number): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/insertmagazzino?id_oggetto=' + id_oggetto,  magazzinoDTO);
  }

  updateMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/updatemagazzino',  magazzinoDTO);
  }

  deleteMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/deletemagazzino',  magazzinoDTO);
  }

  reinsertMagazzino(magazzinoDTO : MagazzinoDTO): Observable<MagazzinoDTO>{
    return this.http.post<MagazzinoDTO>('http://localhost:8080/' + this.type + '/reinsertmagazzino',  magazzinoDTO);
  }
}
