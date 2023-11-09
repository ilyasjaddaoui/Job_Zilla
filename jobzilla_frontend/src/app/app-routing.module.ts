import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignupPageComponent } from './pages/signup/signup-page/signup-page.component';
import { UserSignupComponent } from './pages/signup/user-signup/user-signup.component';
import { RecruiterSignupComponent } from './pages/signup/recruiter-signup/recruiter-signup.component';
import { LoginComponent } from './pages/login/login.component';
import { JobDetailsComponent } from './pages/user/job-details/job-details.component';
import { UserGuard } from './services/auth/user.guard';
import { AdminPageComponent } from './pages/admin/admin-page/admin-page.component';

import { RecruiterGuard } from './services/auth/recruiter.guard';
import { PostJobsComponent } from './pages/admin/post-jobs/post-jobs.component';
import { ManageJobsComponent } from './pages/admin/manage-jobs/manage-jobs.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';



const routes: Routes = [
  
  /*
          ** USER PAGES **
  */
  {path: "", component:HomeComponent, canActivate:[UserGuard]},
  {path:'jobs/:id', component:JobDetailsComponent, canActivate:[UserGuard]},
  

  /*
          ** SIGNUP PAGES **
  */
  {path: "signup", component:SignupPageComponent, children:[
    {path:"", component:UserSignupComponent},
    {path:"recruiter", component:RecruiterSignupComponent}
  ]},
  
  /*
          ** LOGIN PAGES **
  */
  {path:"login", component:LoginComponent},

  /*
          ** ADMIN PAGES **
  */
  {path:"admin", component:AdminPageComponent, canActivate:[RecruiterGuard], children:[
    {path:"", component:DashboardComponent, canActivate:[RecruiterGuard] },
    {path:":userId/post_jobs", component:PostJobsComponent, canActivate:[RecruiterGuard]},
    {path:":userId/manage_jobs", component:ManageJobsComponent, canActivate:[RecruiterGuard]}
    
  ]},
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
