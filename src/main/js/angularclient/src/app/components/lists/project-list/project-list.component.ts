import { Component, OnInit } from '@angular/core';
import { Project } from '../../entities/project'
import { ProjectService } from '../../services/project.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects!: Project[];
  currentProject!: Project;
  message: string = "";

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router) { }
  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    return this.projectService.findAll().subscribe(data => {
      this.projects = data;
    });
  }
  findById(id: number): void {
    this.projectService.findById(id)
      .subscribe(
        project => {
          this.currentProject = project;
          console.log(project);
        },
        error => {
          console.log(error);
        });
  }

  update(id: number): void {
    this.projectService.update(id, this.currentProject)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The project was updated!';
        },
        error => {
          console.log(error);
        });
  }

  delete(id: number): void {
    this.projectService.delete(id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/projects']);
        },
        error => {
          console.log(error);
        });
  }

}
