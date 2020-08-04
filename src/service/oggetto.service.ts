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
    this.type = 'oggetto';
  }

  
}
