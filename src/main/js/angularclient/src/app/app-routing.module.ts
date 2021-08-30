import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProjectComponent } from './create-project/create-project.component';
import { CreateTaskComponent } from './create-task/create-task.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { TaskListComponent } from './task-list/task-list.component';
import { UserListComponent } from './user-list/user-list.component';

const routes: Routes = [
  { path: "projects", component: ProjectListComponent },
  { path: "projects/create", component: CreateProjectComponent },
  { path: "users", component: UserListComponent },
  { path: "users/create", component: CreateUserComponent },
  { path: "tasks", component: TaskListComponent },
  { path: "tasks/create", component: CreateTaskComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
