import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/authentication/login/login.component';
import { AdminDashboardComponent } from './components/admin dashboard/admin-dashboard/admin-dashboard.component';
import { ManageEmployeesComponent } from './components/admin dashboard/manage-employees/manage-employees.component';
import { ManageAssetsComponent } from './components/admin dashboard/manage-assets/manage-assets.component';
import { AssetAuditsComponent } from './components/admin dashboard/asset-audits/asset-audits.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'manage-employees', component: ManageEmployeesComponent },
  { path: 'manage-assets', component: ManageAssetsComponent },
  { path: 'asset-audits', component: AssetAuditsComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
