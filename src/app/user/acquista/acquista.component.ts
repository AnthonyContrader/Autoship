import { Component, OnInit } from '@angular/core';
import { MagazzinoDTO } from 'src/dto/magazzinodto';
import { MagazzinoService } from 'src/service/magazzino.service';
import { UserDTO } from 'src/dto/userdto';
@Component({
  selector: 'app-acquista',
  templateUrl: './acquista.component.html',
  styleUrls: ['./acquista.component.css']
})
export class AcquistaComponent implements OnInit {
  magazzinolist: MagazzinoDTO[];
  codice: string;
  user: UserDTO;
  constructor(private service: MagazzinoService) { }

  ngOnInit(): void {
    this.getMagazzinoList();
    this.codice = JSON.parse(localStorage.getItem('otp'));
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  getMagazzinoList(){
    this.service.getOggettoInCell().subscribe(magazzinolist => this.magazzinolist = magazzinolist);
  }

  setCodice(otp: string, user: UserDTO, magazzino: MagazzinoDTO){
    this.service.setCodice(otp, user.id, magazzino).subscribe(() => this.getMagazzinoList());
  }
}
