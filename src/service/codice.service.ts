import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { CodiceDTO } from 'src/dto/codicedto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodiceService extends AbstractService<CodiceDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.type = 'codice';
  }

  getAllCodes(): Observable<string[]> {
    return this.http.get<string[]>('http://localhost:8080/' + this.type + '/getallcodes')
  }

  confirm(codiceDTO: CodiceDTO): Observable<CodiceDTO> {
    return this.http.post<CodiceDTO>('http://localhost:8080/' + this.type + '/confirm', codiceDTO)
  }

  deleteCode(codiceDTO: CodiceDTO): Observable<CodiceDTO> {
    return this.http.post<CodiceDTO>('http://localhost:8080/' + this.type + '/deletecode', codiceDTO)
  }

}
