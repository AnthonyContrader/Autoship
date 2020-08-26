import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-corriere-menu',
  templateUrl: './corriere-menu.component.html',
  styleUrls: ['./corriere-menu.component.css']
})
export class CorriereMenuComponent implements OnInit {
  isClientCollapsed = false;
  isAccountCollapsed = false;
  isOrdineCollapsed = false;
  constructor(private router: Router) {
  }

  ngOnInit() {
  }
  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }
  accountcollapse() {
    if (this.isAccountCollapsed === false) {
      this.isAccountCollapsed = true;
    } else { this.isAccountCollapsed = false; }
  }
  ordinecollapse() {
    if (this.isOrdineCollapsed === false) {
      this.isOrdineCollapsed = true;
    } else { this.isOrdineCollapsed = false; }
  } 

}
