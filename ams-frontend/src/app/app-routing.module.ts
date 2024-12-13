import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/authentication/login/login.component';
import { AdminDashboardComponent } from './components/admin dashboard/admin-dashboard/admin-dashboard.component';
import { ManageEmployeesComponent } from './components/admin dashboard/manage-employees/manage-employees.component';
import { ManageAssetsComponent } from './components/admin dashboard/manage-assets/manage-assets.component';
import { AssetAuditsComponent } from './components/admin dashboard/asset-audits/asset-audits.component';
import { EmployeeDashboardComponent } from './components/employee dashboard/employee-dashboard/employee-dashboard.component';
import { AssignedAssetsComponent } from './components/employee dashboard/assigned-assets/assigned-assets.component';
import { ServiceRequestComponent } from './components/employee dashboard/service-request/service-request.component';
import { ReturnAssetComponent } from './components/employee dashboard/return-asset/return-asset.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'admin/manage-employees', component: ManageEmployeesComponent },
  { path: 'admin/manage-assets', component: ManageAssetsComponent },
  { path: 'admin/asset-audits', component: AssetAuditsComponent },
  { path: 'employee-dashboard', component: EmployeeDashboardComponent},
  { path: 'assigned-assets', component: AssignedAssetsComponent},
  { path: 'service-request', component: ServiceRequestComponent},
  { path: 'return-asset', component: ReturnAssetComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
