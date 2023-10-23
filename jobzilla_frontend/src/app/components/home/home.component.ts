import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JobResponse } from 'src/app/model/JobResponse';
import { Job } from 'src/app/model/job';
import { JobService } from 'src/app/services/job/job.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  jobs!:Job[];
  jobResponse!: JobResponse;
  keyword = '';

  constructor(private jobService:JobService, private router:Router) { }

  ngOnInit(): void {
    this.getJobs(0, 6)
  }

  getJobs(pageNo: number, pageSize: number): void {
    this.jobService.getAllJobs(pageNo, pageSize, this.keyword)
      .subscribe(response => {
        this.jobResponse = response;
        this.jobs = this.jobResponse.content;
      }, (error) => {
        console.log("Error");
      });
  }


  search(): void {
    this.getJobs(0, 6); // Reset to the first page when performing a new search
  }

  navigateToJobDetails(id: number): void {
    this.router.navigate(['/jobs', id]);
  }

  loadPage(pageNo: number): void {
    if (pageNo >= 0 && pageNo < this.jobResponse?.totalPages) {
      this.getJobs(pageNo, this.jobResponse.pageSize);
    }
  }

  getPages(): number[] {
    const pages: number[] = [];
    const totalPages = this.jobResponse?.totalPages;
    const currentPage = this.jobResponse?.pageNo;

    if (totalPages <= 5) {
      for (let i = 0; i < totalPages; i++) {
        pages.push(i);
      }
    } else {
      // Affiche au maximum 5 pages
      const start = Math.max(0, currentPage - 2);
      const end = Math.min(totalPages - 1, currentPage + 2);

      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
    }

    return pages;
  }

}
