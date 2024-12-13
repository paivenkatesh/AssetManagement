import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Import Components
import { LoginComponent } from './components/authentication/login/login.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { AssignedAssetsComponent } from './components/employee-dashboard/assigned-assets/assigned-assets.component';
import { AssetBorrowingComponent } from './components/employee-dashboard/asset-borrowing/asset-borrowing.component';
import { ServiceRequestComponent } from './components/employee-dashboard/service-request/service-request.component';
import { ReturnAssetComponent } from './components/employee-dashboard/return-asset/return-asset.component';
import { AdminDashboardComponent } from './components/admin dashboard/admin-dashboard/admin-dashboard.component';
import { ManageEmployeesComponent } from './components/admin dashboard/manage-employees/manage-employees.component';
import { ManageAssetsComponent } from './components/admin dashboard/manage-assets/manage-assets.component';
import { AssetAuditsComponent } from './components/admin dashboard/asset-audits/asset-audits.component';
import { HandleServiceRequestsComponent } from './components/admin dashboard/handle-service-requests/handle-service-requests.component';
import { NotFoundComponent } from './components/shared/not-found/not-found.component';
import { UnauthorizedComponent } from './components/shared/unauthorized/unauthorized.component';

// Import Guards
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // Authentication Routes
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // Employee Routes
  {
    path: 'employee-dashboard',
    component: EmployeeDashboardComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['ROLE_USER'] }, // Assuming 'ROLE_USER' is for employees
    children: [
      { path: '', redirectTo: 'assigned-assets', pathMatch: 'full' },
      { path: 'assigned-assets', component: AssignedAssetsComponent },
      { path: 'asset-borrowing', component: AssetBorrowingComponent }, 
      { path: 'service-request', component: ServiceRequestComponent },
      { path: 'return-asset', component: ReturnAssetComponent }
    ]
  },

  // Admin Routes
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['ROLE_ADMIN'] },
    children: [
      { path: '', redirectTo: 'manage-employees', pathMatch: 'full' },
      { path: 'manage-employees', component: ManageEmployeesComponent },
      { path: 'manage-assets', component: ManageAssetsComponent },
      { path: 'asset-audits', component: AssetAuditsComponent },
      { path: 'handle-service-requests', component: HandleServiceRequestsComponent }
    ]
  },

  // Unauthorized & Not Found Routes
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
