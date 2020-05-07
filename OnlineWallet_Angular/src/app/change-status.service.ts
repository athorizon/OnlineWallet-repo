import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { stringify } from 'querystring';

@Injectable({
  providedIn: 'root'
})
export class ChangeStatusService {
  adminId:number;
  constructor(private http:HttpClient,private loginService:LoginService) {
    this.adminId=loginService.adminId;
   }
  changeStatusActive(email:string):Observable<any>
  { 
    return this.http.get("http://localhost:8084/admin/"+this.adminId+"/changestatus?email="+email+"&userStatus=active",{responseType: 'text' as 'json'});
  }
  changeStatusNonActive(email:string):Observable<any>
  { 
    return this.http.get("http://localhost:8084/admin/"+this.adminId+"/changestatus?email="+email+"&userStatus=non_active",{responseType: 'text' as 'json'});
  }
}
