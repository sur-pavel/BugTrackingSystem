import { Component, OnInit } from '@angular/core';
import { Project } from '../../entities/project'
import { ProjectService } from '../../services/project.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  update(id: number){
    this.router.navigate(['update-project', id]);
  }
  findById(id: number): void {
    this.projectService.findById(id)
      .subscribe(
        project => {
          this.currentProject = project;
          console.log(project);
          this.goToProjectDetails(id);
        },
        error => {
          console.log(error);
        });
  }

  
  goToProjectDetails(id: number) {
    this.goToUri(`/projects/${id}`);
  }

  delete(id: number): void {
    this.projectService.delete(id)
      .subscribe(
        response => {
          if (response == null) {
            console.log(`project with id ${id} was deleted`);
          }
          this.goToProjectList();
        },
        error => {
          console.log(error);
        });
  }


  goToProjectList(){
    this.goToUri('/projects')
  }
  goToCreateProject() {
    this.goToUri('/projects/create');
  }
  goToUri(uri: string) {
    this.router.navigate([uri]).then(() => {
      window.location.reload();
    });
  }
}
