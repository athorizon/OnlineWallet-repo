import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate{

  constructor(private loginService: LoginService) {}; 

  canActivate() {
    console.log("OnlyLoggedInUsers");
    if (this.loginService.isLoggedIn()) {
      return true;
    } else {
      window.alert("Access Denied!! You have to login first."); 
      return false;
    }
  }
}
