import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { CodiceDTO } from 'src/dto/codicedto';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodiceService extends AbstractService<CodiceDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservice = 'autoship';
    this.entity = 'codices';
  }

  getAllCodes(): Observable<string[]> {
    return this.http.get<string[]>('http://localhost:8080/' + this.microservice + '/api/getallcodes', {headers: this.headerAuth})
  }

 /* getAllByUser(user: UserDTO): Observable<CodiceDTO[]> {
    return this.http.get<CodiceDTO[]>('http://localhost:8080/' + this.type + '/getallcodesbyuser?user=' + user.id);
  }

  getAllConfirmed(): Observable<CodiceDTO[]> {
    return this.http.get<CodiceDTO[]>('http://localhost:8080/' + this.type + '/getallconfirmed')
  }

  confirm(codiceDTO: CodiceDTO): Observable<CodiceDTO> {
    return this.http.post<CodiceDTO>('http://localhost:8080/' + this.type + '/confirm', codiceDTO)
  }

  send(codiceDTO: CodiceDTO): Observable<CodiceDTO> {
    return this.http.post<CodiceDTO>('http://localhost:8080/' + this.type + '/send', codiceDTO)
  }

  deleteCode(codiceDTO: CodiceDTO): Observable<any> {
    return this.http.delete('http://localhost:8080/' + this.type + '/deletecode?id=' + codiceDTO.id)
  }*/

}
