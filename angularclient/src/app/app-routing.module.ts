import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProjectComponent } from './components/create-entities/create-project/create-project.component';
import { ProjectListComponent } from './components/lists/project-list/project-list.component';
import { ProjectDetailsComponent } from './components/details/project-details/project-details.component';
import { CreateUserComponent } from './components/create-entities/create-user/create-user.component';
import { UserListComponent } from './components/lists/user-list/user-list.component';
import { CreateTaskComponent } from './components/create-entities/create-task/create-task.component';
import { TaskListComponent } from './components/lists/task-list/task-list.component';
import { UpdateProjectComponent } from './components/update-entities/update-project/update-project.component';
import { UpdateUserComponent } from './components/update-entities/update-user/update-user.component';
import { UpdateTaskComponent } from './components/update-entities/update-task/update-task.component';


const routes: Routes = [
  { path: 'projects', component: ProjectListComponent },
  { path: 'projects/create', component: CreateProjectComponent },
  { path: 'projects/update/:id', component: UpdateProjectComponent},
  { path: 'projects/:id', component: ProjectDetailsComponent},
  { path: 'users', component: UserListComponent },
  { path: 'users/create', component: CreateUserComponent },
  { path: 'users/update/:id', component: UpdateUserComponent},
  { path: 'tasks', component: TaskListComponent },
  { path: 'tasks/create', component: CreateTaskComponent },
  { path: 'tasks/update/:id', component: UpdateTaskComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
