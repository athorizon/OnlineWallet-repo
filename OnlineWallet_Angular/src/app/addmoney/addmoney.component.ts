import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AddmoneyService } from '../addmoney.service';

@Component({
  selector: 'app-addmoney',
  templateUrl: './addmoney.component.html',
  styleUrls: ['./addmoney.component.scss']
})
export class AddmoneyComponent implements OnInit {
  addMoneyForm: FormGroup;
  isSubmitted: boolean = false;
  constructor(private formBuilder: FormBuilder, private addMoneyService: AddmoneyService) {
    this.addMoneyForm = this.formBuilder.group({
      amount: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")])
    }

    )
  }
  ngOnInit(): void { }
  onAddMoneyFormSubmit() {
    this.isSubmitted = true;
    if (this.addMoneyForm.valid) {
      console.log("Amount is added successfully", this.addMoneyForm.value);
    }

  }

  enableButton: boolean = false;
  enableAmountButton(): any {
    this.enableButton = true;
  }
  valueDebit: boolean = false;
  public checkDebit(): void {
    if (this.valueDebit == true) {
      this.valueDebit = false;

    }
    else {
      this.valueDebit = true;
      this.valueNetbanking = false;
    }
  }
  valueNetbanking: boolean = false;
  public checkNetBanking(): void {
    if (this.valueNetbanking == true) {
      this.valueNetbanking = false;
    }
    else {
      this.valueNetbanking = true;
      this.valueDebit = false;
    }
  }

  errorMessage: string;
  checkError: boolean = false;
  checkInfo: boolean = false;
  infoMessage: string;

  addMoney(): void {
    if (confirm("Are you sure to add money?")) {
      console.log("Money Added");
      this.addMoneyService.addMoney(this.addMoneyForm.value.amount).subscribe((data) => {
        console.log(data);
        this.infoMessage = "Amount has successfully been added to your account, Your Balance is updated to : " + data;
        this.checkInfo = true;
        this.checkError = false;
      },
        (error) => {
          this.errorMessage = error.error;
          this.checkInfo = false;
          this.checkError = true;
        }
      )
    }
  }
}
