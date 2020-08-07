import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-gestisci-carrello',
  templateUrl: './gestisci-carrello.component.html',
  styleUrls: ['./gestisci-carrello.component.css']
})
export class GestisciCarrelloComponent implements OnInit {
  otp : string;

  constructor(private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.otp = this.router.snapshot.params.otp;
  }

}
