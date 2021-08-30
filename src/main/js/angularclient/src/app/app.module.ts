import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectListComponent } from './projects/project-list/project-list.component';
import { UserListComponent } from './users/user-list/user-list.component';
import { TaskListComponent } from './tasks/task-list/task-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateProjectComponent } from './projects/create-project/create-project.component';
import { CreateUserComponent } from './users/create-user/create-user.component';
import { CreateTaskComponent } from './tasks/create-task/create-task.component'
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    UserListComponent,
    TaskListComponent,
    CreateProjectComponent,
    CreateUserComponent,
    CreateTaskComponent
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
