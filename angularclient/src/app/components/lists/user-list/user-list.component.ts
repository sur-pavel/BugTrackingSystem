import { Component, OnInit } from '@angular/core';
import { User } from '../../entities/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[]
  constructor() {
    this.users = [{
      "id" : 1,
      "firstName": "Nik",
      "lastName" : "Good"
    },
  {
    "id" : 2,
    "firstName": "Took",
    "lastName" : "Reid"
  }]
   }

  ngOnInit(): void {
  }

}
