import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { User } from 'src/app/model/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  

  // Login 
  public login(loginData:any){
    return this.http.post(environment.apiUrl+"/api/auth/user/login", loginData);
  }

  public userRegister(newUser:any){
    return this.http.post(environment.apiUrl+"/api/auth/user/register", newUser, {responseType: 'text'}); // add {responseType: 'text'} for [ Http failure during parsing (ERROR) ] )
  }
  public recruiterRegister(newUser:any){
    return this.http.post(environment.apiUrl+"/api/auth/recruiter/register", newUser, {responseType: 'text'}); // add {responseType: 'text'} for [ Http failure during parsing (ERROR) ] )
  }

  // current user : (ADMIN OR USER)
  public getCurrentUser(){
    return this.http.get<User>(environment.apiUrl+"/api/auth/current");
  }
  
  // set token in localStorage
  public loginUser(token:any){
    localStorage.setItem("token", token);
    // this.loginStatusSubject.next(true);
    return true;
  }

  // isLoggin : user is logged or not
  public isLoggedIn(){
    let tokenStr = localStorage.getItem("token")
    if(tokenStr == undefined || tokenStr == '' || tokenStr==null){
      return false;
    } else{
      return true;
    }
  }

  // logout : remove token from localStorage
  public logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }

  public clear(){
    localStorage.clear();
  }
  // get token
  public getToken(){
    return localStorage.getItem("token");
  }

  // set userDetail
  public setUser(user:any){
    localStorage.setItem("user", JSON.stringify(user));
  }

  // get User
  public getUser(){
    let userStr= localStorage.getItem("user")
    if(userStr!=null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }

  // get userRole
  public getUserRole(){
    let user = this.getUser()
    return user.authorities[0].authority;
  }

}
