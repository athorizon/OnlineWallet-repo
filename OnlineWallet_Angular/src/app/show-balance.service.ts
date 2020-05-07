import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';


@Injectable({
  providedIn: 'root'
})
export class ShowBalanceService {
  userId:number
  constructor(private http:HttpClient,private login:LoginService) {
   this.userId=this.login.userId;
   }
   
   

  showBalance():Observable<any>{
    return this.http.get("http://localhost:8084/showbalance/"+this.userId);
  }
}
