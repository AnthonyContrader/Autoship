import { Component, OnInit } from '@angular/core';
import { CodiceService } from 'src/service/codice.service';
import { CodiceDTO } from 'src/dto/codicedto';
@Component({
  selector: 'app-spedizione',
  templateUrl: './spedizione.component.html',
  styleUrls: ['./spedizione.component.css']
})
export class SpedizioneComponent implements OnInit {

  codicelist: CodiceDTO[];

  constructor(private service: CodiceService) { }

  ngOnInit(): void {
  this.getAllConfirmed();
  }

  getAllConfirmed() {
    this.service.getAllConfirmed().subscribe(codicelist => this.codicelist = codicelist);
  }
  spedizione(codice: CodiceDTO){
    this.service.send(codice).subscribe(() => this.getAllConfirmed());
  }

  
}






