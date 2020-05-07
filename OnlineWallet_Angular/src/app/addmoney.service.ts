import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { LoginService } from './login.service';
import { stringify } from 'querystring';

@Injectable({
  providedIn: 'root'
})
export class AddmoneyService {
  userId: number;
  constructor(private http: HttpClient, private loginService: LoginService) {
    this.userId = loginService.userId;
  }
  body: string;
  addMoney(amount: number): Observable<any> {
    return this.http.put("http://localhost:8084/addmoney/" + this.userId + "?amount=" + amount, { body: this.body });
  }

}
