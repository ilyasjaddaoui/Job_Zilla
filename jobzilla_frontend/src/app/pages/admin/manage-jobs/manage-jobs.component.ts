import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Job } from 'src/app/model/job';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { JobService } from 'src/app/services/job/job.service';

@Component({
  selector: 'app-manage-jobs',
  templateUrl: './manage-jobs.component.html',
  styleUrls: ['./manage-jobs.component.css']
})
export class ManageJobsComponent implements OnInit {

  jobs!: Job[];
  userId!: number;
  user!:User;
  isLoggedIn = false;
  

  constructor(private jobService:JobService, private auth:AuthService, private route:ActivatedRoute) { }

  ngOnInit(): void {

    this.isLoggedIn = this.auth.isLoggedIn();
    this.user = this.auth.getUser();
    this.userId = this.user.userId;
    this.jobService.getJobsByUserId(this.userId).subscribe(
      (data : any) =>{
        this.jobs = data;
        console.log(data);
      }, (error: any)=>{
        console.log(error);
      }
    )
  }

  
}
