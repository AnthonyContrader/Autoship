import { Component, OnInit } from '@angular/core';
import { CodiceDTO } from 'src/dto/codicedto';
import { CodiceService } from 'src/service/codice.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.css']
})
export class CarrelloComponent implements OnInit {

  codicelist: CodiceDTO[];
  user: UserDTO;

  constructor(private service: CodiceService, private router: Router) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.getCodeList(this.user);
  }

  getCodeList(user: UserDTO){
    this.service.getAllByUser(user).subscribe(codicelist => this.codicelist = codicelist);
  }

  conferma(codice: CodiceDTO){
    this.service.confirm(codice).subscribe(() => this.getCodeList(this.user));
  }

  gestisciCarrello(codice: CodiceDTO){
    this.router.navigate(['/user-dashboard/carrello/gestisci-carrello/' + codice.otp]);;
  }

  visualizzaCarrello(codice: CodiceDTO){
    this.router.navigate(['/user-dashboard/carrello/visualizza-carrello/' + codice.otp]);;
  }

  deleteCarrello(codice: CodiceDTO){
    this.service.deleteCode(codice).subscribe(() => this.getCodeList(this.user));
  }

}
