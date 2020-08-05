import { Component, OnInit } from '@angular/core';
import { MagazzinoService } from 'src/service/magazzino.service';
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

  constructor(private service: MagazzinoService) { }

  ngOnInit() {
    this.getMagazzinoList();
  }

  
  getMagazzinoList() {
    this.service.getAll().subscribe(magazzinolist => this.magazzinolist = magazzinolist);
  }

  insertMagazzino(magazzino : MagazzinoDTO){
    this.service.insertMagazzino(magazzino).subscribe(() => this.getMagazzinoList());
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

  clear(){
    this.magazzinotoinsert = new MagazzinoDTO();
  }

}
