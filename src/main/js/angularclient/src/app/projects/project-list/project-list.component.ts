import { Component, OnInit } from '@angular/core';
import { Project } from '../project'
import { ProjectService } from '../project.service';
@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects!: Project[];

  constructor(private projectService: ProjectService) {
    this.getProjects();
  }
  private getProjects() {
    this.projectService.findAll().subscribe(data => {
      this.projects = data;
    })
  }

  ngOnInit(): void {

  }

}
