import { Component, OnInit } from '@angular/core';
import ProjectService from '../../../service/project.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../entities/project';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})

export class ProjectDetailsComponent implements OnInit {

  id!: number;
  project!: Project;
  message = '';


  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router) { }


  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.project = new Project();
    this.projectService.findById(this.id).subscribe(data => {
      this.project = data;
    });
  }


}


