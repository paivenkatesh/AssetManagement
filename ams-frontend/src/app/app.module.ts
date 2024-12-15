// app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
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
import { EmployeeDashboardComponent } from './components/employee dashboard/employee-dashboard/employee-dashboard.component';
import { AssignedAssetsComponent } from './components/employee dashboard/assigned-assets/assigned-assets.component';
import { ServiceRequestComponent } from './components/employee dashboard/service-request/service-request.component';
import { ReturnAssetComponent } from './components/employee dashboard/return-asset/return-asset.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { EmployeeService } from './services/employee.service';
import { IssueTypeService } from './services/issue-type.service';
import { ServiceRequestService } from './services/service-request.service';
import { FormsModule } from '@angular/forms';
import { HandleServiceRequestsComponent } from './components/admin dashboard/handle-service-requests/handle-service-requests.component';
import { AssetCategoryService } from './services/asset-category.service';
import { RoleService } from './services/role.service';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { AuthService } from './services/auth.service';
import { AssetBorrowingComponent } from './components/employee dashboard/asset-borrowing/asset-borrowing.component';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './components/shared/not-found/not-found.component';
import { UnauthorizedComponent } from './components/shared/unauthorized/unauthorized.component';
import { JwtModule } from '@auth0/angular-jwt';

export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminDashboardComponent,
    ManageEmployeesComponent,
    ManageAssetsComponent,
    AssetAuditsComponent,
    EmployeeDashboardComponent,
    AssignedAssetsComponent,
    ServiceRequestComponent,
    ReturnAssetComponent,
    RegisterComponent,
    HandleServiceRequestsComponent,
    NavbarComponent,
    FooterComponent,
    AssetBorrowingComponent,
    NotFoundComponent,
    UnauthorizedComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["localhost:8080"], 
        disallowedRoutes: [
          "http://localhost:8080/api/auth/authenticate",
          "http://localhost:8080/api/auth/register" 
        ]
      }
    })
  ],

  providers: [
    AssetAuditService,
    AssetBorrowingService,
    AssetCategoryService,
    AssetService,
    EmployeeService,
    IssueTypeService,
    RoleService,
    ServiceRequestService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }