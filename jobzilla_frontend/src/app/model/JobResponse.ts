import { Job } from "./job";

export interface JobResponse {
    content: Job[] ;
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;
  }