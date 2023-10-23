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
  id!: number;
  user!:User;

  constructor(private jobService:JobService, private authService:AuthService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    
  }

  createJobs(){
    this.authService.getUser().subscribe(
      (userData: User) => {
        this.user = userData;
        this.id = this.user.id;
        console.log(this.id);
      },
      (error: any) => {
        console.error('Error fetching user data:', error);
      }
    );

  }

}
