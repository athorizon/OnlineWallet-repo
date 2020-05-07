import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { TransferFundService } from '../transfer-fund.service';

@Component({
  selector: 'app-transferfund',
  templateUrl: './transferfund.component.html',
  styleUrls: ['./transferfund.component.scss']
})
export class TransferfundComponent implements OnInit {
  transferFundForm: FormGroup;
  isSubmitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private tranferFundService: TransferFundService) {
    this.transferFundForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")]),
      amount: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*")])
    }

    )
  }
  ngOnInit() {

  }
  errorMessage: string;
  checkError: boolean = false;
  checkInfo: boolean = false;
  infoMessage: string;
  onTransferFundFormSubmit() {
    this.isSubmitted = true;
    if (confirm("Do you really want to tranfer money?")) {
      if (this.transferFundForm.valid) {
        console.log("Fund is transferred successfully", this.transferFundForm.value);
        this.tranferFundService.transferFund(this.transferFundForm.value.amount, this.transferFundForm.value.email).subscribe(
          (data) => {
            this.infoMessage = data;
            this.checkInfo = true;
          },
          (error) => {
            this.errorMessage = error.error;
            this.checkError = true;
          }
        );
      }
    }
  }
}




