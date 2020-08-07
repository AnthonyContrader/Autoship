import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-superuser-menu',
  templateUrl: './superuser-menu.component.html',
  styleUrls: ['./superuser-menu.component.css']
})
export class SuperuserMenuComponent implements OnInit {
  isUserCollapsed = false;
  isClientCollapsed = false;
  isAccountCollapsed = false;
  isOggettoCollapsed = false;
  isMagazzinoCollapsed = false;
  isCarrelloCollapsed = false;
  isOrdineCollapsed = false;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  userscollapse() {
    if (this.isUserCollapsed === false) {
      this.isUserCollapsed = true;
    } else { this.isUserCollapsed = false; }
  }

  accountcollapse() {
    if (this.isAccountCollapsed === false) {
      this.isAccountCollapsed = true;
    } else { this.isAccountCollapsed = false; }
  }
  oggettocollapse() {
    if (this.isOggettoCollapsed === false) {
      this.isOggettoCollapsed = true;
    } else { this.isOggettoCollapsed = false; }
  } 

  magazzinocollapse() {
    if (this.isMagazzinoCollapsed === false) {
      this.isMagazzinoCollapsed = true;
    } else { this.isMagazzinoCollapsed = false; }
  }
  carrellocollapse() {
    if (this.isCarrelloCollapsed === false) {
      this.isCarrelloCollapsed = true;
    } else { this.isCarrelloCollapsed = false; }
  } 
  ordinecollapse() {
    if (this.isOrdineCollapsed === false) {
      this.isOrdineCollapsed = true;
    } else { this.isOrdineCollapsed = false; }
  } 

}
