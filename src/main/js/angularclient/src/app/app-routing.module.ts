import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProjectComponent } from './projects/create-project/create-project.component';
import { CreateTaskComponent } from './tasks/create-task/create-task.component';
import { CreateUserComponent } from './users/create-user/create-user.component';
import { ProjectListComponent } from './projects/project-list/project-list.component';
import { TaskListComponent } from './tasks/task-list/task-list.component';
import { UserListComponent } from './users/user-list/user-list.component';

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
