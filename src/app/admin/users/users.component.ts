import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  admin : UserDTO;
  users: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();
  
  constructor(private service: UserService) { }

  ngOnInit() {
    this.admin = JSON.parse(localStorage.getItem('currentUser'));
    this.getUsers();
  }

  getUsers() {
    this.service.getAllUsers().subscribe(users => this.users = users);
  }

  delete(user: UserDTO) {
    this.service.delete(user.id).subscribe(() => this.getUsers());
  }

  deleteUser(user: UserDTO){
    this.service.deleteUser(user).subscribe(() => this.getUsers());
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => this.getUsers());
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => this.getUsers());
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
