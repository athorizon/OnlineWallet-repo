import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class TransferFundService {
  userId:number;
  constructor(private http:HttpClient,private login:LoginService) { 
    this.userId=this.login.userId;
  }
  email:string;
  amount:number;


  transferFund(userAmount:number, userEmail:string):Observable<any>
  {
    return this.http.get("http://localhost:8084/transactmoney/"+this.userId+"?amount="+userAmount+"&email="+userEmail,{responseType: 'text' as 'json'});
  }
}
