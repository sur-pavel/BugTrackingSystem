import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../entities/project';
import { ProjectService } from '../../../service/project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  project: Project = new Project();
  
  constructor(private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService
    ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.projectService.save(this.project).subscribe(result => this.gotoProjectList())
  }
  gotoProjectList(): void {
    this.router.navigate(["/projects"]);
  }
}

