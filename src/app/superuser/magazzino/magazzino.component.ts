import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
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
  magazzinofulllist: number[];
  @ViewChild('magazzinoTable') wrapper: ElementRef;
//  isLast = false;

  oggettolist: OggettoDTO[];
  oggettotoinsert : OggettoDTO;
  id_oggetto : number;

  constructor(private service: MagazzinoService, private oggettoService: OggettoService) { }

  ngOnInit() {
    this.oggettotoinsert = new OggettoDTO;
    this.getMagazzinoList();
    this.getOggettoList();
  //  this.getMagazzinoWithOggetto();
  //  document.addEventListener('DOMContentLoaded', (event) => {
  //    this.selectOggetto();
 //   });
  }

  //ngAfterViewChecked() {
  //  this.selectOggetto();
  // }

  
  getMagazzinoList() {
    this.service.getAll().subscribe(magazzinolist => this.magazzinolist = magazzinolist);
  }

  insertMagazzino(magazzino : MagazzinoDTO){
    this.service.insertMagazzino(magazzino, this.id_oggetto).subscribe(() => this.getMagazzinoList());
    this.oggettoService.getOggettoNotInCell().subscribe(() => this.getOggettoList());
    this.id_oggetto = 0;
  }

 updateMagazzino(magazzino : MagazzinoDTO, index: number){
    this.id_oggetto = Number.parseInt((document.getElementById("select"+ index) as HTMLSelectElement).value);
  //  console.log(this.id_oggetto);
    console.log(magazzino);
    if(this.id_oggetto && magazzino.oggettoId && this.id_oggetto == magazzino.oggettoId){
        this.oggettotoinsert.id = magazzino.oggettoId;
    }
    else if(this.id_oggetto){
      this.oggettolist.forEach(o => {
          if(o.id == this.id_oggetto){
            this.oggettotoinsert = o
          }
      });
    }
    else{
      this.oggettotoinsert.id = 0;
    }
    console.log(this.oggettotoinsert);

    //console.log(document.getElementById("select"+ index));
    //console.log(index);
    this.service.updateMagazzino(magazzino, this.oggettotoinsert.id).subscribe(() => this.getMagazzinoList());
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

  /*selectOggetto(magazzino: MagazzinoDTO){
   (this.wrapper.nativeElement as HTMLElement).querySelectorAll("tr").forEach(tr => {
    let select = tr.querySelector('select') as HTMLSelectElement;
    if(select){
      if(magazzino.oggetto){
        select.selectedIndex = 0;
      }
      else{
        select.selectedIndex = select.options.length;
        console.log(select);
      }
    }
   });
  //  console.log(m);


  }*/

/*  check(isFirst: boolean, magazzino: MagazzinoDTO){
    if(isFirst){
      console.log("inCheck");
      console.log(magazzino.oggetto);
      this.selectOggetto(magazzino);
    }
    return true;
  }*/

/*  getMagazzinoWithOggetto(){
    this.service.getMagazzinoWithOggetto().subscribe(magazzinofulllist => this.magazzinofulllist = magazzinofulllist);
  }*/

 clear(){
    this.magazzinotoinsert = new MagazzinoDTO();
    this.id_oggetto = 0;
  }

}
