import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
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
