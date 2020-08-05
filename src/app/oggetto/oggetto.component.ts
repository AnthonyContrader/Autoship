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
// oggetto :OggettoComponent ;
//oggettotoinsert: OggettoDTO = new OggettoDTO();
  constructor(private service : OggettoService) { }
  ngOnInit() {
    this.oggettoList();
  }

  public oggettoList(){
    this.service.getAll().subscribe(oggetti => this.oggetti = oggetti);
  }

 /*let oggetti = service.getall();
  
      }

      oggettolist() {
        this.oggetto.service.getAll().subscribe((response) => {
          this.oggettiDTO= response;
      
 
 this.oggetto.Service.getall().subscribe((response) => {
    this.oggettoInsert = response;
*/    

}
