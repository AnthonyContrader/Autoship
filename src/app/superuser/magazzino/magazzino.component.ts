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
  oggettotoinsert : OggettoDTO;
  id_oggetto : number;

  constructor(private service: MagazzinoService, private oggettoService: OggettoService) { }

  ngOnInit() {
    this.oggettotoinsert = new OggettoDTO;
    this.getMagazzinoList();
    this.getOggettoList();
  }

  
  getMagazzinoList() {
    this.service.getAll().subscribe(magazzinolist => this.magazzinolist = magazzinolist);
  }

  insertMagazzino(magazzino : MagazzinoDTO, oggetto: OggettoDTO){
    this.oggettotoinsert = magazzino.oggetto;
    magazzino.oggetto = null;
    this.service.insertMagazzino(magazzino, this.oggettotoinsert).subscribe(() => this.getMagazzinoList());
    this.oggettoService.getOggettoNotInCell().subscribe(() => this.getOggettoList());
  }

  updateMagazzino(magazzino : MagazzinoDTO, oggetto: OggettoDTO, index: number){
    this.id_oggetto = Number.parseInt((document.getElementById("select"+ index) as HTMLSelectElement).value);
  //  console.log(this.id_oggetto);
    console.log(magazzino);
    if(this.id_oggetto && magazzino.oggetto && this.id_oggetto == magazzino.oggetto.id){
        this.oggettotoinsert = magazzino.oggetto;
    }
    else if(this.id_oggetto){
      this.oggettolist.forEach(o => {
          if(o.id == this.id_oggetto){
            this.oggettotoinsert = o
          }
      });
    }
    else{
      this.oggettotoinsert = null;
    }
    console.log(this.oggettotoinsert);

    //console.log(document.getElementById("select"+ index));
    //console.log(index);
    magazzino.oggetto = null;

    this.service.updateMagazzino(magazzino, this.oggettotoinsert).subscribe(() => this.getMagazzinoList());
    this.oggettoService.getOggettoNotInCell().subscribe(() => this.getOggettoList());
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
