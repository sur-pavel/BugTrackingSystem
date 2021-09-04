import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../entities/project';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  id!: number;
  project!: Project;
  message!: string;
  form: FormGroup;
  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
    public fb: FormBuilder
  ) {
    this.form = this.fb.group({
      title: ['']
    });
    }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.projectService.findById(this.id).subscribe(data => {
      this.project = data;
    }, error => console.log(error));
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
  update(id: number): void {
    this.projectService.update(id, this.project)
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
