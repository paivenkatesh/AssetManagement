import { Component, OnInit } from '@angular/core';
import { AssetService } from '../../../services/asset.service';
import { AssetBorrowingService } from '../../../services/asset-borrowing.service';
import { AssetBorrowing, BorrowingStatus } from '../../../models/asset-borrowing.model';
import { Asset } from '../../../models/asset.model';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-return-asset',
  templateUrl: './return-asset.component.html',
  styleUrls: ['./return-asset.component.css']
})
export class ReturnAssetComponent implements OnInit {
  activeBorrowings: AssetBorrowing[] = [];
  successMessage: string = '';
  errorMessage: string = '';
  employeeId: number | null = null;

  constructor(
    private assetBorrowingService: AssetBorrowingService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.employeeId = this.authService.getEmployeeId();
    if (this.employeeId !== null) {
      this.fetchActiveBorrowings();
    } else {
      this.errorMessage = 'Invalid employee ID.';
    }
  }

  /**
   * Fetches all active borrowings for the employee.
   */
  fetchActiveBorrowings(): void {
    this.assetBorrowingService.getBorrowingsByEmployee(this.employeeId!).subscribe({
      next: (data) => this.activeBorrowings = data.filter(borrowing => borrowing.status === BorrowingStatus.Active),
      error: (err) => this.errorMessage = 'Failed to load active borrowings.'
    });
  }

  /**
   * Initiates the return process for an asset.
   * @param borrowingId The ID of the borrowing record.
   */
  returnAsset(borrowingId: number): void {
    if (confirm('Are you sure you want to return this asset?')) {
      this.assetBorrowingService.returnAsset(borrowingId).subscribe({
        next: (data) => {
          this.successMessage = 'Asset returned successfully!';
          this.fetchActiveBorrowings();
        },
        error: (err) => this.errorMessage = 'Failed to return asset.'
      });
    }
  }
}
