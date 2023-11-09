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
    this.jobService.postJobs(this.job, this.userId).subscribe(
      (data: any) => {
        console.log(data);
        console.log("Success");
      },
      (error: any) => {
        console.log("Error:", error);
      }
    );

  }

}
