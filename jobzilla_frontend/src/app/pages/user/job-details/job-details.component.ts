import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Job } from 'src/app/model/job';
import { JobService } from 'src/app/services/job/job.service';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  id!:number;
  job!:Job;

  constructor(private jobService:JobService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.job = new Job();
    this.jobService.getJobsById(this.id).subscribe(
      data =>{
        this.job = data;
        console.log(data);
      }, error=>{
        console.log(error);
      }
    )
  }

}
