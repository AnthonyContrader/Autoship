import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
@Component({
  selector: 'app-corriere-dashboard',
  templateUrl: './corriere-dashboard.component.html',
  styleUrls: ['./corriere-dashboard.component.css']
})
export class CorriereDashboardComponent implements OnInit {

  user: UserDTO;
  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }


}
