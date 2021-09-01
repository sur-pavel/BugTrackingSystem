import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent {

  form: FormGroup;

  constructor(private router: Router,
    private projectService: ProjectService,
    public fb: FormBuilder
  ) {
    this.form = this.fb.group({
      title: ['']
    });
  }


  submitForm() {
    let formObj = this.form.getRawValue();
    this.projectService.save(formObj).subscribe(data => {
      console.log(data);
      this.goToProjectList();
    },
      error => console.log(error));
  }

  goToProjectList() {
    this.router.navigate(['/projects']);
  }
}

