import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { CoreModule } from './core';
import { MaterialModule } from './material.module';
import { ActivityListComponent } from './activities/activity-list/activity-list.component';
import { ActivityAddEditComponent } from './activities/activity-add-edit/activity-add-edit.component';
import { NavigatorComponent } from './navigator/navigator.component';
import { LogoutComponent } from './logout/logout.component';
import { ContactListComponent } from './contacts/contact-list/contact-list.component';
import { ContactAddEditComponent } from './contacts/contact-add-edit/contact-add-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ActivityListComponent,
    ActivityAddEditComponent,
    NavigatorComponent,
    LogoutComponent,
    ContactListComponent,
    ContactAddEditComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
