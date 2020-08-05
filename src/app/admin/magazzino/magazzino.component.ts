import { Component, OnInit } from '@angular/core';
import { MagazzinoService } from 'src/service/magazzino.service';
import { OggettoService } from 'src/service/oggetto.service';
import { MagazzinoDTO } from 'src/dto/magazzinodto';
import { OggettoDTO } from 'src/dto/oggettodto';

@Component({
  selector: 'app-magazzino',
  templateUrl: './magazzino.component.html',
  styleUrls: ['./magazzino.component.css']
})
export class MagazzinoComponent implements OnInit {

  magazzinolist: MagazzinoDTO[];
  magazzinotoinsert: MagazzinoDTO = new MagazzinoDTO();

  oggettolist: OggettoDTO[];

  constructor(private service: MagazzinoService, private oggettoService: OggettoService) { }

  ngOnInit() {
    this.getMagazzinoList();
    this.getOggettoList();
  }

  
  getMagazzinoList() {
    this.service.getAll().subscribe(magazzinolist => this.magazzinolist = magazzinolist);
  }

  insertMagazzino(magazzino : MagazzinoDTO, id_oggetto : number){
    this.service.insertMagazzino(magazzino, id_oggetto).subscribe(() => this.getMagazzinoList());
  }

  updateMagazzino(magazzino : MagazzinoDTO){
    this.service.updateMagazzino(magazzino).subscribe(() => this.getMagazzinoList());
  }

  deleteMagazzino(magazzino : MagazzinoDTO){
    this.service.deleteMagazzino(magazzino).subscribe(() => this.getMagazzinoList());
  }

  reinsertMagazzino(magazzino : MagazzinoDTO){
    this.service.reinsertMagazzino(magazzino).subscribe(() => this.getMagazzinoList());
  }

  getOggettoList(){
    this.oggettoService.getOggettoNotInCell().subscribe(oggettolist => this.oggettolist = oggettolist);
  }

  clear(){
    this.magazzinotoinsert = new MagazzinoDTO();
  }

}
