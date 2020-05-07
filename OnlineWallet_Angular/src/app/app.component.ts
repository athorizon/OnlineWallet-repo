import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Welcome to Online Wallet!';
  constructor(private routes: Router, private loginService: LoginService) { }
  ngOnInit(): void {
    this.routes.navigateByUrl("/homepage");


  }
  setUserId(): any {
    this.loginService.userId = undefined;

  }
  setAdminId(): any {
    this.loginService.adminId = undefined;
  }
}
