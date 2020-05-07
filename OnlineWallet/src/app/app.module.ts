import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { AppRoutingModule } from './app-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { AddmoneyComponent } from './addmoney/addmoney.component';
import { HomepageComponent } from './homepage/homepage.component';
import { TransferfundComponent } from './transferfund/transferfund.component';
import { ShowbalanceComponent } from './showbalance/showbalance.component';
import { HttpClientModule } from '@angular/common/http';
import { ViewUserComponent } from './view-user/view-user.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AuthguardService } from './authguard.service';
@NgModule({
  declarations: [
    AppComponent,
    RegisteruserComponent,
    AddmoneyComponent,
    HomepageComponent,
    TransferfundComponent,
    ShowbalanceComponent,
    ViewUserComponent,
    AdminLoginComponent
],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AuthguardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
