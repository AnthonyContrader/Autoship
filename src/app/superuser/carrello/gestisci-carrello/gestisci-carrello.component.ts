import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarrelloDTO } from 'src/dto/carrellodto';
import { CarrelloService } from 'src/service/carrello.service';

@Component({
  selector: 'app-gestisci-carrello',
  templateUrl: './gestisci-carrello.component.html',
  styleUrls: ['./gestisci-carrello.component.css']
})
export class GestisciCarrelloComponent implements OnInit {
  otp: string;
  carrellolist : CarrelloDTO[];

  constructor(private service: CarrelloService, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.otp = this.router.snapshot.params.otp;
    this.getCarrello(this.otp);
  }

  getCarrello(otp: string){
    return this.service.getCarrello(otp).subscribe(carrellolist => this.carrellolist = carrellolist);
  }

  deleteCarrello(carrello: CarrelloDTO){
    this.service.deleteCarrello(carrello).subscribe(() => this.getCarrello(this.otp));
  }

}
