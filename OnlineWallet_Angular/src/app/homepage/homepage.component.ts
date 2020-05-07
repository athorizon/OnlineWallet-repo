import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {
  myLoginForm: FormGroup;
  isSubmitted: boolean = false;
  userId: number;
  constructor(private formBuilder: FormBuilder, private loginService: LoginService) {
    this.myLoginForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z0-9_.+-]+@[a-zA-Z]+.[a-zA-Z]+$")]),
      password: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z]+@[0-9]*$"), Validators.minLength(4), Validators.maxLength(16)])
      // email:new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")]),
      // password:new FormControl('',[Validators.required,Validators.pattern("^[a-zA-Z]+@[0-9]$"),Validators.minLength(4),Validators.maxLength(16)])
    })
  };

  ngOnInit() {

  }
  errorMessage: string;
  checkError: boolean = false;
  checkInfo: boolean = false;
  infoMessage: string;

  onLoginFormSubmit() {
    this.isSubmitted = true;
    if (this.myLoginForm.valid) {
      console.log("User Login Form Submitted", this.myLoginForm.value);
      this.loginService.loginUser(this.myLoginForm.value.email, this.myLoginForm.value.password).subscribe((data) => { this.userId = data; this.checkInfo = true; this.infoMessage = "You have succesfully logged in" }, (error) => {
        this.errorMessage = error.error;
        this.checkError = true
      });

    }
  }
}  
