import { Component, OnInit } from '@angular/core';
import { AssetBorrowingService } from '../../../services/asset-borrowing.service';
import { AssetService } from '../../../services/asset.service';
import { AuthService } from '../../../services/auth.service';
import { Asset } from '../../../models/asset.model';
import { AssetBorrowing, BorrowingStatus } from '../../../models/asset-borrowing.model';

@Component({
  selector: 'app-asset-borrowing',
  templateUrl: './asset-borrowing.component.html',
  styleUrls: ['./asset-borrowing.component.css']
})
export class AssetBorrowingComponent implements OnInit {
  allAssets: Asset[] = [];
  employeeBorrowings: AssetBorrowing[] = [];
  selectedAssetId: number = 0;
  successMessage: string = '';
  errorMessage: string = '';
  isLoading: boolean = false;
  employeeId: number | null = null;

  constructor(
    private assetBorrowingService: AssetBorrowingService,
    private assetService: AssetService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.employeeId = this.authService.getEmployeeId();
    if (this.employeeId !== null) {
      this.fetchAllAssets();
      this.fetchEmployeeBorrowings();
    } else {
      this.errorMessage = 'Invalid employee ID.';
    }
  }

  /**
   * Fetches all assets from the backend.
   */
  fetchAllAssets(): void {
    this.assetService.getAllAssets().subscribe({
      next: (data) => this.allAssets = data,
      error: (err) => this.errorMessage = 'Failed to load assets.'
    });
  }

  /**
   * Fetches borrowings associated with the logged-in employee.
   */
  fetchEmployeeBorrowings(): void {
    this.assetBorrowingService.getBorrowingsByEmployee(this.employeeId!).subscribe({
      next: (data) => this.employeeBorrowings = data,
      error: (err) => this.errorMessage = 'Failed to load your borrowings.'
    });
  }

  /**
   * Submits a new asset borrowing request.
   */
  borrowAsset(): void {
    if (this.selectedAssetId === 0) {
      this.errorMessage = 'Please select an asset to borrow.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.assetBorrowingService.borrowAsset(this.employeeId!, this.selectedAssetId).subscribe({
      next: (data) => {
        this.successMessage = `Successfully borrowed asset: ${data.asset.assetName}`;
        this.selectedAssetId = 0;
        this.isLoading = false;
        this.fetchAllAssets();
        this.fetchEmployeeBorrowings();
      },
      error: (err) => {
        this.errorMessage = 'Failed to borrow asset. It might already be borrowed.';
        this.isLoading = false;
      }
    });
  }

  /**
   * Initiates the return process for a borrowed asset.
   * @param borrowingId The ID of the borrowing record.
   */
  returnAsset(borrowingId: number): void {
    if (confirm('Are you sure you want to return this asset?')) {
      this.assetBorrowingService.returnAsset(borrowingId).subscribe({
        next: (data) => {
          this.successMessage = `Successfully returned asset: ${data.asset.assetName}`;
          this.fetchAllAssets();
          this.fetchEmployeeBorrowings();
        },
        error: (err) => this.errorMessage = 'Failed to return asset. Please try again.'
      });
    }
  }

  /**
   * Checks if the asset is available for borrowing.
   * @param asset The asset to check.
   * @returns True if available, else false.
   */
  isAssetAvailable(asset: Asset): boolean {
    return asset.status === 'Available';
  }
}
