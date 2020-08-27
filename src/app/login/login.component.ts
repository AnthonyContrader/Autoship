import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { CodiceService } from 'src/service/codice.service';
import { Router } from '@angular/router';
import { CodiceDTO } from 'src/dto/codicedto';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;
  userDTO: UserDTO = new UserDTO();;
  codiceList : string[];

  constructor(private service: UserService,private codiceService: CodiceService, private router: Router) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username, f.value.password);
    let codice: number;
    const currentUser = 'currentUser';
    const otp='otp';
  //  this.checkCodice();

    this.service.login(this.loginDTO).subscribe((response: any) => {

      localStorage.setItem("currentAuth", JSON.stringify({ "authorities": response.id_token }));

      if(response != null){
        this.service.getUserLogged(this.loginDTO.username).subscribe((response: any) => {
          console.log("Response: " + response);

          this.userDTO.id = response.id;
          this.userDTO.role = response.authorities;
          this.userDTO.username = this.loginDTO.username;
          this.userDTO.password = this.loginDTO.password;

          localStorage.setItem(currentUser, JSON.stringify(this.userDTO));

          var controllo="";

          controllo=this.userDTO.role[0];
          console.log("Controllo: " + controllo);

          switch (controllo) {
            case "ROLE_SUPERUSER": {
            /* codice=Math.floor((Math.random() * 1000) + 1);
              while(this.codiceList.includes(codice.toString())){
                codice=Math.floor((Math.random() * 1000) + 1);
              }
              localStorage.setItem(otp,JSON.stringify(codice));*/
              this.router.navigate(['/superuser-dashboard']);
              break;
            }
            case "ROLE_ADMIN": {
              this.router.navigate(['/admin-dashboard']);
              break;
            }
            case "ROLE_CORRIERE": {
              this.router.navigate(['/corriere-dashboard']);
              break;
            }
            case "ROLE_USER": {
            /* codice=Math.floor((Math.random() * 1000) + 1);
              while(this.codiceList.includes(codice.toString())){
                codice=Math.floor((Math.random() * 1000) + 1);
              }
              localStorage.setItem(otp,JSON.stringify(codice));*/
              this.router.navigate(['/user-dashboard']);
              break;
            }
            default:
              this.router.navigate(['/login']);
          }
        });
      }
    });
  }

  checkCodice(){
    return this.codiceService.getAllCodes().subscribe(codes => {this.codiceList = codes as string[]});
  }
}
