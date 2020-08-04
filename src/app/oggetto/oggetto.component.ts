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
  
  oggettiDto: Array<OggettoDTO>
  
  constructor(private service : OggettoService) { }
  ngOnInit() {
  }


 let oggetti = service.getall();
  
 
 this.oggetto.Service.getall().subscribe((response) => {
    this.oggettoInsert = response;
    

}
