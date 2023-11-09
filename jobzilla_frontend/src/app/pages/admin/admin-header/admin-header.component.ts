import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  isLoggedIn = false;
  user!: User;
  userId: any;
  

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.auth.isLoggedIn();
    this.user = this.auth.getUser();
    this.userId = this.user.userId;
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
