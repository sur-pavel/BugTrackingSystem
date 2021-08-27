import { Component, OnInit } from '@angular/core';
import { Task } from '../task';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: Task[]
  constructor() {
    this.tasks = [{
    "theme" : "App test",
    "description" : "fdlkjfa;jk4aj Test improve",
    "priority": 1,
    "taskType" : "Bug"
    }]
   }

  ngOnInit(): void {
  }

}
