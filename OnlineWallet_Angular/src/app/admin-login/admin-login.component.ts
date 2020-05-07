import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {
  adminLoginForm: FormGroup;
  isSubmitted: boolean = false;
  constructor(private formBuilder: FormBuilder, private loginService: LoginService) {

    this.adminLoginForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z0-9_.+-]+@[a-zA-Z]+.[a-zA-Z]+$")]),
      password: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z]+@[0-9]*$")]),

    });
  }
  ngOnInit() {
  }
  errorMessage: string;
  checkError: boolean = false;
  checkInfo: boolean = false;
  infoMessage: string;
  onAdminLoginFormSubmit() {
    this.isSubmitted = true;
    if (this.adminLoginForm.valid) {
      console.log("Admin Login Form Submitted", this.adminLoginForm.value);
      this.loginService.loginAdmin(this.adminLoginForm.value.email, this.adminLoginForm.value.password).subscribe(
        (data) => {
          this.infoMessage = "You have successfully logged in";
          this.checkInfo = true;
          this.checkError = false;
        },
        (error) => {
          this.errorMessage = error.error;
          this.checkError = true;
          this.checkInfo = false;
        }
      )
    }

  }

}
