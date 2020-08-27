import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
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
export class UserService extends AbstractService<UserDTO>{

  constructor(http: HttpClient) {
    super(http);
  }

 /* login(loginDTO: LoginDTO): Observable<UserDTO> {
    return this.http.post<any>('http://localhost:8080/' + this.type + '/login', loginDTO)
  }*/

/*  login(loginDTO: LoginDTO): Observable<UserDTO> {
    return this.http.post<any>('http://localhost:8080/' + this.type + '/login', {login: loginDTO.username, password: loginDTO.password})
  }*/

  login(logindto:LoginDTO) {
     return this.http.post('http://localhost:8080/api/authenticate', logindto);
 
   }

   getUserLogged(username: string) {
      return this.http.get('http://localhost:8080/api/users/' + username, {
        headers: {
            Authorization: localStorage.getItem('currentAuth')
        }
      });
    }

/*  getAllUsers(): Observable<UserDTO[]> {
    return this.http.get<UserDTO[]>('http://localhost:8080/' + this.type + '/getallusers')
  }

  getAllAdmin(): Observable<number[]> {
    return this.http.get<number[]>('http://localhost:8080/' + this.type + '/getalladmin')
  }

  deleteUser(userDTO: UserDTO): Observable<any>{
    return this.http.delete('http://localhost:8080/' + this.type + '/deleteuser?id=' + userDTO.id);
  } */
}
