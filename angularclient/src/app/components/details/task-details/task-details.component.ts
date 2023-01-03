import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Task } from '../../entities/task';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  id!: number;
  task!: Task;
  message = '';

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    ) { }


    ngOnInit(): void {
      this.id = this.route.snapshot.params['id'];
      this.task = new Task();
      this.taskService.findById(this.id).subscribe(data => {
        // this.task = data;
      });
  }
}
