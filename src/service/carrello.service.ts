import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { CarrelloDTO } from 'src/dto/carrellodto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarrelloService extends AbstractService<CarrelloDTO>{

  constructor(http: HttpClient) { 
    super(http);
    this.microservice = 'autoship';
    this.entity = 'carrellos';
  }

  getCarrello(otp : string): Observable<CarrelloDTO[]> {
    return this.http.get<CarrelloDTO[]>('http://localhost:8080/' + this.microservice + '/api/getcarrello/' + otp, {headers: this.headerAuth});
  }

  deleteCarrello(carrelloDTO: CarrelloDTO): Observable<any> {
    return this.http.delete('http://localhost:8080/' + this.microservice + '/api/deletecarrello/' + carrelloDTO.id, {headers: this.headerAuth});
  }
  
}
