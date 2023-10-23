import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  isLoggedIn = false;
  user!: User;

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.auth.isLoggedIn();
    this.user = this.auth.getUser();
    this.auth.loginStatusSubject.asObservable().subscribe(
      (data)=>{
        this.isLoggedIn = this.auth.isLoggedIn();
        this.user = this.auth.getUser();
      }
    )
  }

  public logout(){
    this.auth.logout();
    this.router.navigate(['/login']);
  }

}
