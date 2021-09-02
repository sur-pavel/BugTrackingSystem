import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../entities/project';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  currentProject!: Project;
  message!: string;
  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
  }

  update(id: number): void {
    this.projectService.update(id, this.currentProject)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The project was updated!';
          this.router.navigate(["/projects"]).then(() => {
            window.location.reload();
          });
        },
        error => {
          console.log(error);
        });
  }
}
