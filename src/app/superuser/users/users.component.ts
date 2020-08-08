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
  admins: number[];
  usertoinsert: UserDTO = new UserDTO();
  
  constructor(private service: UserService) { }

  ngOnInit() {
    this.admin = JSON.parse(localStorage.getItem('currentUser'));
    this.getUsers();
    this.getAdmin();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.users = users);
  }

  getAdmin() {
    this.service.getAllAdmin().subscribe(admins => this.admins = admins);
  }

  delete(user: UserDTO) {
    this.service.delete(user.id).subscribe(() => this.getUsers());
  }

  deleteUser(user: UserDTO){
    this.service.deleteUser(user).subscribe(() => this.getUsers());
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => this.getUsers());
    this.service.getAllAdmin().subscribe(() => this.getAdmin());
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => this.getUsers());
    this.service.getAllAdmin().subscribe(() => this.getAdmin());
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
