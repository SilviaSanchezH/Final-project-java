import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivityListComponent } from './activities/activity-list/activity-list.component';
import { AuthGuard } from './auth/guard/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { ContactListComponent } from './contacts/contact-list/contact-list.component';

const routes: Routes = [
  {path: '', redirectTo: 'activities', pathMatch: "full"},
  {path: 'activities', component: ActivityListComponent, canActivate: [AuthGuard]},
  {path: 'contacts', component: ContactListComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
