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
import { NavigatorComponent } from './navigator/navigator.component';
import { LogoutComponent } from './logout/logout.component';
import { ContactListComponent } from './contacts/contact-list/contact-list.component';
import { ConfirmationDialogComponent } from './dialogs/confirmation-dialog/confirmation-dialog.component';
import { EditActivityComponent } from './dialogs/edit-activity/edit-activity.component';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { EditContactComponent } from './dialogs/edit-contact/edit-contact.component';
import { ActivityDetailsComponent } from './dialogs/activity-details/activity-details.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ActivityListComponent,
    NavigatorComponent,
    LogoutComponent,
    ContactListComponent,
    ConfirmationDialogComponent,
    EditActivityComponent,
    EditContactComponent,
    ActivityDetailsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    CoreModule,
    NgxMaterialTimepickerModule
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'es-ES'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

