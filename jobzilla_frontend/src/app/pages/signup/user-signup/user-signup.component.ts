import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

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
    this.auth.userRegister(this.newUser).subscribe(
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
