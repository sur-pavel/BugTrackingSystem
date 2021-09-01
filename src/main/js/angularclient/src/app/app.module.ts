import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ProjectListComponent } from './components/lists/project-list/project-list.component';
import { UserListComponent } from './components/lists/user-list/user-list.component';
import { TaskListComponent } from './components/lists/task-list/task-list.component';
import { CreateProjectComponent } from './components/create-entities/create-project/create-project.component';
import { CreateUserComponent } from './components/create-entities/create-user/create-user.component';
import { CreateTaskComponent } from './components/create-entities/create-task/create-task.component'
import { ProjectDetailsComponent } from './components/details/project-details/project-details.component';
import { UserDetailsComponent } from './components/details/user-details/user-details.component';
import { TaskDetailsComponent } from './components/details/task-details/task-details.component';

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
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
