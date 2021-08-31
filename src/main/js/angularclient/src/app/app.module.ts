import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectListComponent } from './components/lists/project-list/project-list.component';
import { UserListComponent } from './components/lists/user-list/user-list.component';
import { TaskListComponent } from './components/lists/task-list/task-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateProjectComponent } from './components/create-entity/create-project/create-project.component';
import { CreateUserComponent } from './components/create-entity/create-user/create-user.component';
import { CreateTaskComponent } from './components/create-entity/create-task/create-task.component'
import { FormsModule } from '@angular/forms';
import { ProjectDetailsComponent } from './components/details/project-details/project-details.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { TaskDetailsComponent } from './task-details/task-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    UserListComponent,
    TaskListComponent,
    CreateProjectComponent,
    CreateUserComponent,
    CreateTaskComponent,
    ProjectDetailsComponent,
    UserDetailsComponent,
    TaskDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
