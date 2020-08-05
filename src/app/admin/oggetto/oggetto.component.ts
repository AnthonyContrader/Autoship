
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
    constructor(private service : OggettoService) { }
    ngOnInit() {
      this.oggettoList();
    }
  
    public oggettoList(){
      this.service.getAll().subscribe(oggetti => this.oggetti = oggetti);
    }
}
   
