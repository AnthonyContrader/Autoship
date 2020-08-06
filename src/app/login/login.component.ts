import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { CodiceService } from 'src/service/codice.service';
import { Router } from '@angular/router';
import { CodiceDTO } from 'src/dto/codicedto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;
  codiceList : string[];

  constructor(private service: UserService,private codiceService: CodiceService, private router: Router) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username, f.value.password);
    let codice: number;
    const currentUser = 'currentUser';
    const otp='otp';
    this.checkCodice();

    this.service.login(this.loginDTO).subscribe((user) => {

      if (user != null) {
        localStorage.setItem(currentUser, JSON.stringify(user));

        switch (user.usertype.toString()) {
          case 'SUPERUTENTE': {
            codice=Math.floor((Math.random() * 1000) + 1);
            while(this.codiceList.includes(codice.toString())){
              codice=Math.floor((Math.random() * 1000) + 1);
            }
            localStorage.setItem(otp,JSON.stringify(codice));
            this.router.navigate(['/superuser-dashboard']);
            break;
          }
          case 'AMMINISTRATORE': {
            this.router.navigate(['/admin-dashboard']);
            break;
          }
          case 'CORRIERE': {
            this.router.navigate(['/corriere-dashboard']);
            break;
          }
          case 'UTENTE': {
            codice=Math.floor((Math.random() * 1000) + 1);
            while(this.codiceList.includes(codice.toString())){
              codice=Math.floor((Math.random() * 1000) + 1);
            }
            localStorage.setItem(otp,JSON.stringify(codice));
            this.router.navigate(['/user-dashboard']);
            break;
          }
          default:
            this.router.navigate(['/login']);
        }
      }
    });
  }

  checkCodice(){
    return this.codiceService.getAllCodes().subscribe(codes => {this.codiceList = codes as string[]});
  }
}
