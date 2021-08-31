import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Project } from '../../entities/project';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  project: Project = new Project();


  constructor(private router: Router,
    private projectService: ProjectService,
  ) { }

  ngOnInit(): void {
  }

  onSubmit(f: NgForm) {
    console.log(f.value);
    console.log(f.valid);
    this.saveProject();
  }

  saveProject() {
    this.projectService.save(this.project).subscribe(data => {
      console.log(data);
      this.goToProjectList();
    },
      error => console.log(error));
  }

  goToProjectList() {
    this.router.navigate(['/projects']);
  }
}

