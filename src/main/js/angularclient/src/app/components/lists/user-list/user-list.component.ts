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
      "firstName": "Nik",
      "lastName" : "Good"
    },
  {
    "firstName": "Took",
    "lastName" : "Reid"
  }]
   }

  ngOnInit(): void {
  }

}
