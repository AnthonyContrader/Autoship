import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';
import { timingSafeEqual } from 'crypto';


@Component({
  selector: 'app-general-info',
  templateUrl: './general-info.component.html',
  styleUrls: ['./general-info.component.css']
})
export class GeneralInfoComponent implements OnInit {

  user: UserDTO;
  userRead: UserDTO;

  constructor(private service: UserService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.read(this.user);
  }

  read(user: UserDTO) {
    this.service.read(user.id).subscribe(user => this.userRead = user);
  }

}
