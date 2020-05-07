import { Component, OnInit } from '@angular/core';
import { ShowBalanceService } from '../show-balance.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-showbalance',
  templateUrl: './showbalance.component.html',
  styleUrls: ['./showbalance.component.scss']
})
export class ShowbalanceComponent implements OnInit {
  balance: number;
  constructor(private showBalance: ShowBalanceService) { }

  errorMessage: string;
  checkError: boolean = false;
  ngOnInit(): void {
    this.showBalance.showBalance().subscribe(
      (data) => {
        this.balance = data;
      },
      (error) => {
        this.errorMessage = error.error;
        this.checkError = true;
      }
    );

  }

}
