import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  

  constructor(private jobService:JobService, private auth:AuthService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {

    this.isLoggedIn = this.auth.isLoggedIn();
    this.user = this.auth.getUser();
    this.userId = this.user.userId;
    this.getJobs();
  }

  getJobs(){
    this.jobService.getJobsByUserId(this.userId).subscribe(
      (data : any) =>{
        this.jobs = data;
        console.log(data);
      }, (error: any)=>{
        console.log(error);
      }
    )
  }

  navigateToJobDetails(id: number): void {
    this.router.navigate(['/jobs', id]);
  }

  deleteJob(id:number){
    this.jobService.deleteJob(id).subscribe(data=>{
      alert("job deleted with successfuly")
      this.getJobs();
      
    })
  }

  
}
