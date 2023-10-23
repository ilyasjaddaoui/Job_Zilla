import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData={
    usernameOrEmail:'',
    password:''
  }
  

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  formSubmit(){
    this.auth.login(this.loginData).subscribe(
      (data: any) => {
        console.log(data);
        console.log("success");
        
        // set token to localstorage
        this.auth.loginUser(data.token);

        this.auth.getCurrentUser().subscribe((user:any)=>{
          this.auth.setUser(user);
          // redirect ...RECRUITER OR USER
          if(this.auth.getUserRole() == "RECRUITER"){
            // recruiter page
            this.router.navigate(['/admin']);
          }else if(this.auth.getUserRole() == "USER"){
            // user page
            this.router.navigate(['']);
            this.auth.loginStatusSubject.next(true);
            console.log(user);
          }else{
            this.auth.logout();
            alert("Invalid username or password");
          }
        });
      },
      (error: any) => {
        console.log(error);
      }
    )
  }
  

}
