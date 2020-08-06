import { Component, OnInit } from '@angular/core';
import { OggettoDTO } from 'src/dto/oggettodto';
import { OggettoService } from 'src/service/oggetto.service';
import { UserDTO } from 'src/dto/userdto';
@Component({
  selector: 'app-acquista',
  templateUrl: './acquista.component.html',
  styleUrls: ['./acquista.component.css']
})
export class AcquistaComponent implements OnInit {
  oggettolist: OggettoDTO[];
  codice: string;
  user: UserDTO;
  constructor(private oggettoservice: OggettoService) { }

  ngOnInit(): void {
    this.getOggettoList();
    this.codice = JSON.parse(localStorage.getItem('otp'));
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }
  getOggettoList(){
    this.oggettoservice.getOggettoInCell().subscribe(oggettolist => this.oggettolist = oggettolist);
  }
  setCodice(otp: string, id_user: number, id_oggetto: number){
    this.oggettoservice.setCodice(otp,id_user,id_oggetto);
    this.getOggettoList();
  }
  clear(oggetto: OggettoDTO){
    oggetto = new OggettoDTO();
  }
}
