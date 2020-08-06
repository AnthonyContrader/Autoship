import { Component, OnInit } from '@angular/core';
import { OggettoDTO } from 'src/dto/oggettodto';
import { OggettoService } from 'src/service/oggetto.service';
@Component({
  selector: 'app-acquista',
  templateUrl: './acquista.component.html',
  styleUrls: ['./acquista.component.css']
})
export class AcquistaComponent implements OnInit {
  oggettolist: OggettoDTO[];
  constructor(private oggettoservice: OggettoService) { }

  ngOnInit(): void {
    this.getOggettoList();
  }
  getOggettoList(){
    this.oggettoservice.getOggettoInCell().subscribe(oggettolist => this.oggettolist = oggettolist);
  }
}
