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
submitForm() {
    var formData: any = new FormData();
    formData.append("name", this.form.get('name').value);
    formData.append("avatar", this.form.get('avatar').value);

    this.http.post('http://localhost:4000/api/create-user', formData).subscribe(
      (response) => console.log(response),
      (error) => console.log(error)
    )
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

