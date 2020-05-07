import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import {Routes,RouterModule} from '@angular/router';
import { AddmoneyComponent } from './addmoney/addmoney.component';
import { HomepageComponent } from './homepage/homepage.component';
import { TransferfundComponent } from './transferfund/transferfund.component';
import { ShowbalanceComponent } from './showbalance/showbalance.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ViewUserComponent } from './view-user/view-user.component';
import { AuthguardService } from './authguard.service';
import { AuthguardAdminService } from './authguard-admin.service';
const routes:Routes=[
  {path:'homepage',component:HomepageComponent},
  {path:'registeruser',component:RegisteruserComponent},
  {path:'addmoney',component:AddmoneyComponent,canActivate:[AuthguardService]},
  {path:'transferfund',component:TransferfundComponent,canActivate:[AuthguardService]},
  {path:'showbalance',component:ShowbalanceComponent,canActivate:[AuthguardService]},
  {path:'adminlogin', component:AdminLoginComponent},
  {path:'viewuser', component:ViewUserComponent,canActivate:[AuthguardAdminService]}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports:[RouterModule]
  
})
export class AppRoutingModule { }
