import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobResponse } from 'src/app/model/JobResponse';
import { Job } from 'src/app/model/job';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(private http:HttpClient) { }


getAllJobs(pageNo: number, pageSize: number, keyword?: string): Observable<JobResponse> {
  let params = new HttpParams()
    .set('pageNo', pageNo.toString())
    .set('pageSize', pageSize.toString());

  if (keyword) {
    params = params.set('keyword', keyword);
  }

  return this.http.get<JobResponse>(`${environment.apiUrl}/api/jobs`, { params });
}

 
  public getJobsById(id:number):Observable<Job>{
    return this.http.get<Job>(`${environment.apiUrl+"/api/jobs"}/${id}`);
  }

  public postJobs(job:Job, userId:number){
    return this.http.post(`${environment.apiUrl}/api/users/${userId}/add_job`, job);
  }

  public getJobsByUserId(userId:number):Observable<Job[]>{
    return this.http.get<Job[]>(`${environment.apiUrl}/api/users/${userId}/jobs`);
  }

 
}
