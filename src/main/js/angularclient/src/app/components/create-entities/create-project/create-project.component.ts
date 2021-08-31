import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../entities/project';
import { ProjectService } from '../../services/project.service';

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
    this.projectService.save(this.project).subscribe(result => 
      this.router.navigate(["/projects"]))
  }
}

