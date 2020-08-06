import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {
  isClientCollapsed = false;
  isAccountCollapsed = false;
  isOggettoCollapsed = false;
  constructor(private router: Router) { }

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
  oggettocollapse() {
    if (this.isOggettoCollapsed === false) {
      this.isOggettoCollapsed = true;
    } else { this.isOggettoCollapsed = false; }
  } 
}
