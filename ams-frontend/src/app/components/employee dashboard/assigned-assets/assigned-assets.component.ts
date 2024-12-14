import { Component, OnInit } from '@angular/core';
import { AssetService } from '../../../services/asset.service';
import { Asset } from '../../../models/asset.model';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-assigned-assets',
  templateUrl: './assigned-assets.component.html',
  styleUrls: ['./assigned-assets.component.css']
})
export class AssignedAssetsComponent implements OnInit {
  assets: Asset[] = [];
  successMessage: string = '';
  errorMessage: string = '';
  employeeId: number | null = null;

  constructor(
    private assetService: AssetService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.employeeId = this.authService.getEmployeeId();
    if (this.employeeId !== null) {
      this.fetchAssignedAssets();
    } else {
      this.errorMessage = 'Invalid employee ID.';
    }
  }

  /**
   * Fetches assets assigned to the logged-in employee.
   */
  fetchAssignedAssets(): void {
    this.assetService.getAssetsByEmployee(this.employeeId!).subscribe({
      next: (data) => this.assets = data,
      error: (err) => this.errorMessage = 'Failed to load assigned assets.'
    });
  }
}
