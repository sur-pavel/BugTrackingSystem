import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProjectComponent } from './components/create-entity/create-project/create-project.component';
import { CreateTaskComponent } from './components/create-entity/create-task/create-task.component';
import { CreateUserComponent } from './components/create-entity/create-user/create-user.component';
import { ProjectListComponent } from './components/lists/project-list/project-list.component';
import { TaskListComponent } from './components/lists/task-list/task-list.component';
import { UserListComponent } from './components/lists/user-list/user-list.component';

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
