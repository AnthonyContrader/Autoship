import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  user: UserDTO;
  userUpdated: UserDTO;

  constructor(private service: UserService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.read(this.user);
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => this.user);
  }

  read(user: UserDTO) {
    this.service.read(user.id).subscribe(() => this.user);
  }

}
