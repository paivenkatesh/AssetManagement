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
  // method to fetch all assets
  fetchAllAssets(): void {
    this.assetService.getAllAssets().subscribe({
      next: (data) => this.allAssets = data,
      error: (err) => this.errorMessage = 'Failed to load assets.'
    });
  }
  // method to fetch current employee borrowings
  fetchEmployeeBorrowings(): void {
    this.assetBorrowingService.getBorrowingsByEmployee(this.employeeId!).subscribe({
      next: (data) => this.employeeBorrowings = data,
      error: (err) => this.errorMessage = 'Failed to load your borrowings.'
    });
  }
  // method to borrow asset
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
// method to return the borrowed asset
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

  isAssetAvailable(asset: Asset): boolean {
    return asset.status === 'Available';
  }
}
