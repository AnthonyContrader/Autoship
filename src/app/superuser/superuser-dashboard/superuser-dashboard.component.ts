import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-superuser-dashboard',
  templateUrl: './superuser-dashboard.component.html',
  styleUrls: ['./superuser-dashboard.component.css']
})
export class SuperuserDashboardComponent implements OnInit {

  user: UserDTO;

  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

}
