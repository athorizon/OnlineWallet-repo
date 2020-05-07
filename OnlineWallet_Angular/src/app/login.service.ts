import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  email:string;
  password:string;
  userId:number;
  adminId:number;
  isLoggedIn():boolean{
    if(this.userId==undefined)
    {return false;}
    return true;
  }
  isAdminLoggedIn():boolean{
    if(this.adminId==undefined)
    {return false;}
    return true;
  }
  constructor(private http:HttpClient){
   
  }
  params=new HttpParams();
  request:Observable<any>;
loginUser(userEmail:string, userPassword:string):Observable<any>{
  this.request= this.http.get("http://localhost:8084/login",{params:{email: userEmail, password: userPassword}});
  this.request.subscribe((data)=>{this.userId=data});
  return this.request;
}

loginAdmin(userEmail:string, userPassword:string):Observable<any>{
  this.request= this.http.get("http://localhost:8084/admin",{params:{email: userEmail, password: userPassword}});
  this.request.subscribe((data)=>{this.adminId=data});
  return this.request;
}
}
