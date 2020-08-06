import { Component, OnInit } from '@angular/core';
import { CodiceDTO } from 'src/dto/codicedto';
import { CodiceService } from 'src/service/codice.service';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.css']
})
export class CarrelloComponent implements OnInit {

  codicelist: CodiceDTO[];

  constructor(public service: CodiceService) { }

  ngOnInit(): void {
    this.getCodeList();
  }

  getCodeList(){
    this.service.getAll().subscribe(codicelist => this.codicelist = codicelist);
  }

  conferma(codice: CodiceDTO){
    this.service.confirm(codice).subscribe(() => this.getCodeList());
  }

}
