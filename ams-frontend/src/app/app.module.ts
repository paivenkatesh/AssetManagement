import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { AdminDashboardComponent } from './components/admin dashboard/admin-dashboard/admin-dashboard.component';
import { ManageEmployeesComponent } from './components/admin dashboard/manage-employees/manage-employees.component';
import { ManageAssetsComponent } from './components/admin dashboard/manage-assets/manage-assets.component';
import { AssetAuditsComponent } from './components/admin dashboard/asset-audits/asset-audits.component';
import { AssetAuditService } from './services/asset-audit.service';
import { AssetBorrowingService } from './services/asset-borrowing.service';
import { AssetService } from './services/asset.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminDashboardComponent,
    ManageEmployeesComponent,
    ManageAssetsComponent,
    AssetAuditsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    AssetAuditService,
    AssetBorrowingService,
    AssetService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
