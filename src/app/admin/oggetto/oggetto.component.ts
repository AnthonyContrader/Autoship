
import { Component, OnInit } from '@angular/core';
import { OggettoDTO } from 'src/dto/oggettodto';
import { NgForm } from '@angular/forms';
import { OggettoService } from 'src/service/oggetto.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-oggetto',
  templateUrl: './oggetto.component.html',
  styleUrls: ['./oggetto.component.css']
})
export class OggettoComponent implements OnInit {

  oggetti: OggettoDTO[];
  oggettotoinsert: OggettoDTO = new OggettoDTO();
    constructor(private service : OggettoService) { }
    ngOnInit() {
      this.oggettoList();
    }
  
    public oggettoList(){
      this.service.getAll().subscribe(oggetti => this.oggetti = oggetti);
    }

    insertOggetto(oggetto : OggettoDTO){
      this.service.insert(oggetto).subscribe(() => this.oggettoList());
    }
    updateOggetto(oggetto : OggettoDTO){
      this.service.update(oggetto).subscribe(() => this.oggettoList());
    }
  
    deleteOggetto(oggetto : OggettoDTO){
      this.service.deleteOggetto(oggetto).subscribe(() => this.oggettoList());
    }

    reinsertOggetto(oggetto : OggettoDTO){
      this.service.reinsertOggetto(oggetto).subscribe(() => this.oggettoList());
    }

    clear(){
      this.oggettotoinsert = new OggettoDTO();
    }
}
   
