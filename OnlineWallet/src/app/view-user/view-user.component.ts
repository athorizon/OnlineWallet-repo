import { Component, OnInit } from '@angular/core';
import { GetUserService } from '../get-user.service';
import { ChangeStatusService } from '../change-status.service';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.scss']
})
export class ViewUserComponent implements OnInit {
  activeEmails: string[] = [];
  nonActiveEmails: string[] = [];

  constructor(private getUserService: GetUserService, private changeStatusService: ChangeStatusService) { }

  ngOnInit(): void {
  }
  valueActiveUser: boolean = false;
  public checkActiveUser(): void {
    if (this.valueActiveUser == true) {
      this.valueActiveUser = false;
    }
    else {
      this.getUserService.getActiveUser().subscribe((data) => { this.activeEmails = data });
      this.valueActiveUser = true;
      this.valueNonActiveUser = false;
    }
  }
  valueNonActiveUser: boolean = false;
  public checkNonActiveUser(): void {
    if (this.valueNonActiveUser == true) {
      this.valueNonActiveUser = false;
    }
    else {
      this.getUserService.getNonActiveUser().subscribe((data) => { this.nonActiveEmails = data });
      this.valueNonActiveUser = true;
      this.valueActiveUser = false;
    }
  }
  errorMessageActive: string;
  checkErrorActive: boolean = false;
  checkInfoActive: boolean = false;
  infoMessageActive: string;
  changeStatusActive(email: string): void {

    this.changeStatusService.changeStatusActive(email).subscribe(
      (data) => {
        console.log(data);
        this.infoMessageActive = "" + data + " has been successfully Activated";
        this.checkInfoActive = true;
        this.checkInfoInActive = false;
        this.checkErrorInActive = false;
        this.checkErrorActive = false;
      },
      (error) => {
        console.log(error);
        this.errorMessageActive = error.error;
        this.checkInfoActive = false;
        this.checkInfoInActive = false;
        this.checkErrorInActive = false;
        this.checkErrorActive = true;
      }
    );
  }
  errorMessageInActive: string;
  checkErrorInActive: boolean = false;
  checkInfoInActive: boolean = false;
  infoMessageInActive: string;
  changeStatusNonActive(email: string): void {

    this.changeStatusService.changeStatusNonActive(email).subscribe(
      (data) => {
        console.log(data);
        this.infoMessageInActive = "" + data + " has been successfully De-Activated";
        this.checkInfoActive = false;
        this.checkInfoInActive = true;
        this.checkErrorInActive = false;
        this.checkErrorActive = false;
      },
      (error) => {
        console.log(error);
        this.errorMessageInActive = error.error;
        this.checkInfoActive = false;
        this.checkInfoInActive = false;
        this.checkErrorInActive = true;
        this.checkErrorActive = false;
      }
    );
  }
}
