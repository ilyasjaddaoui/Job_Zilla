import { User } from 'src/app/model/user';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Job } from 'src/app/model/job';
import { AuthService } from 'src/app/services/auth/auth.service';
import { JobService } from 'src/app/services/job/job.service';

@Component({
  selector: 'app-post-jobs',
  templateUrl: './post-jobs.component.html',
  styleUrls: ['./post-jobs.component.css']
})
export class PostJobsComponent implements OnInit {
  job: Job = new Job();
  userId!: number;
  user!:User;
  isLoggedIn = false;


  constructor(private jobService:JobService, private auth:AuthService, private route:ActivatedRoute) { }

  ngOnInit(): void {

    this.isLoggedIn = this.auth.isLoggedIn();
    this.user = this.auth.getUser();
    this.userId = this.user.userId; 
  }

  createJobs(){

    if(this.job.companyLogo=='' || this.job.companyLogo == null){
      alert("Please enter companyLogo");
      return;
    }

    if(this.job.companyName =='' || this.job.companyName == null){
      alert("Please enter companyName");
      return;
    }
    if(this.job.country =='' || this.job.country == null){
      alert("Please enter country");
      return;
    }
    if(this.job.city =='' || this.job.city == null){
      alert("Please enter city");
      return;
    }
    if(this.job.description =='' || this.job.description == null){
      alert("Please enter description");
      return;
    }
    if(this.job.emailAddress =='' || this.job.emailAddress == null){
      alert("Please enter emailAddress");
      return;
    }
    if(this.job.experience =='' || this.job.experience == null){
      alert("Please enter experience");
      return;
    }
    if(this.job.jobCategory =='' || this.job.jobCategory == null){
      alert("Please enter jobCategory");
      return;
    }
    if(this.job.jobTitle =='' || this.job.jobTitle == null){
      alert("Please enter jobTitle");
      return;
    }
    if(this.job.jobType =='' || this.job.jobType == null){
      alert("Please enter jobType");
      return;
    }
    if(this.job.offeredSalary =='' || this.job.offeredSalary == null){
      alert("Please enter offeredSalary");
      return;
    }
    if(this.job.qualification =='' || this.job.qualification == null){
      alert("Please enter qualification");
      return;
    }
    if(this.job.jobType =='' || this.job.jobType == null){
      alert("Please enter jobType");
      return;
    }

    this.jobService.postJobs(this.job, this.userId).subscribe(
      (data: any) => {
        // console.log(data);
        alert("job created with successfuly")
        console.log("Success");
      },
      (error: any) => {
        alert("somthing wrong !!")
        console.log("Error:", error);
      }
    );

  }

}
