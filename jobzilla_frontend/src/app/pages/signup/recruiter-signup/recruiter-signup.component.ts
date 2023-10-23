import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-recruiter-signup',
  templateUrl: './recruiter-signup.component.html',
  styleUrls: ['./recruiter-signup.component.css']
})
export class RecruiterSignupComponent implements OnInit {

  public newUser={
    username:'',
    email:'',
    password:'',
    phone:''
  };

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  formSubmit(){
    this.auth.recruiterRegister(this.newUser).subscribe(
      (data: any) => {
        console.log(data);
        console.log("success");
        this.router.navigate(['/login']);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
