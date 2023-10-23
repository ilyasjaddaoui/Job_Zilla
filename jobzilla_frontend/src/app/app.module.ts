import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { UserSignupComponent } from './pages/signup/user-signup/user-signup.component';
import { RecruiterSignupComponent } from './pages/signup/recruiter-signup/recruiter-signup.component';
import { SignupPageComponent } from './pages/signup/signup-page/signup-page.component';
import { LeftSideComponent } from './pages/signup/left-side/left-side.component';

import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { AuthInterceptorProviders } from './services/auth/auth.interceptor';
import { JobDetailsComponent } from './pages/user/job-details/job-details.component';
import { AdminPageComponent } from './pages/admin/admin-page/admin-page.component';
import { PostJobsComponent } from './pages/admin/post-jobs/post-jobs.component';
import { ManageJobsComponent } from './pages/admin/manage-jobs/manage-jobs.component';
import { AdminHeaderComponent } from './pages/admin/admin-header/admin-header.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';





@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    UserSignupComponent,
    RecruiterSignupComponent,
    SignupPageComponent,
    LeftSideComponent,
    LoginComponent,
    JobDetailsComponent,
    AdminPageComponent,
    PostJobsComponent,
    ManageJobsComponent,
    AdminHeaderComponent,
    DashboardComponent,
    
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    RouterModule
  ],
  providers: [AuthInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
