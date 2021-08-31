import { Component, OnInit } from '@angular/core';
import ProjectService from '../../../service/project.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})

export class ProjectDetailsComponent implements OnInit {

  currentProject = null;
  message = '';


  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router) { }

  
    ngOnInit(): void {
      this.message = '';
      this.getProject(this.route.snapshot.paramMap.get('id'));
    }
  
    getProject(id: number): void {
      this.projectService.read(id)
        .subscribe(
          project => {
            this.currentProject = project;
            console.log(project);
          },
          error => {
            console.log(error);
          });
    }
  
    setAvailableStatus(status): void {
      const data = {
        name: this.currentProject.name,
        description: this.currentProject.description,
        available: status
      };
  
      this.projectService.update(this.currentProject.id, data)
        .subscribe(
          response => {
            this.currentProject.available = status;
            console.log(response);
          },
          error => {
            console.log(error);
          });
    }
  
    updateProject(): void {
      this.projectService.update(this.currentProject.id, this.currentProject)
        .subscribe(
          response => {
            console.log(response);
            this.message = 'The project was updated!';
          },
          error => {
            console.log(error);
          });
    }
  
    deleteProject(): void {
      this.projectService.delete(this.currentProject.id)
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
  
}
