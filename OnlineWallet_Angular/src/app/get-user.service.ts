import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
@Injectable({
  providedIn: 'root'
})
export class GetUserService {
  adminId:number;
  constructor(private http:HttpClient,private loginService:LoginService) {
    this.adminId=loginService.adminId;
   }

  getActiveUser():Observable<any>
  {
    return this.http.get("http://localhost:8084/admin/"+this.adminId+"/userlist?userStatus=active");
  }
  getNonActiveUser():Observable<any>
  {
    return this.http.get("http://localhost:8084/admin/"+this.adminId+"/userlist?userStatus=non_active");
  }
}
