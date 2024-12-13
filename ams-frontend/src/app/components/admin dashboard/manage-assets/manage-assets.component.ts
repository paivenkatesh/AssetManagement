import { Component, OnInit } from '@angular/core';
import { AssetService } from '../../../services/asset.service';
import { Asset, AssetStatus } from '../../../models/asset.model';
import { AssetCategory } from '../../../models/asset-category.model';

@Component({
  selector: 'app-manage-assets',
  templateUrl: './manage-assets.component.html',
  styleUrls: ['./manage-assets.component.css']
})
export class ManageAssetsComponent implements OnInit {
  assets: Asset[] = [];
  newAsset: Asset = {
    assetId: 0,
    assetName: '',
    category: { categoryId: 0, categoryName: '' },
    assetModel: '',
    manufacturingDate: '',
    expiryDate: '',
    assetValue: 0,
    status: AssetStatus.Available
  };
  successMessage: string = '';
  errorMessage: string = '';
  editingAsset: Asset | null = null;

  constructor(private assetService: AssetService) { }

  ngOnInit(): void {
    this.fetchAllAssets();
    
  }

  fetchAllAssets(): void {
    this.assetService.getAllAssets().subscribe({
      next: (data) => this.assets = data,
      error: (err) => this.errorMessage = err
    });
  }

  // Method to add a new asset
  addAsset(form: any): void {
    if (form.invalid) {
      return;
    }
    this.assetService.addAsset(this.newAsset).subscribe({
      next: (data) => {
        this.successMessage = 'Asset added successfully!';
        this.fetchAllAssets();
        form.resetForm();
        this.newAsset = {
          assetId: 0,
          assetName: '',
          category: { categoryId: 0, categoryName: '' },
          assetModel: '',
          manufacturingDate: '',
          expiryDate: '',
          assetValue: 0,
          status: AssetStatus.Available
        };
      },
      error: (err) => this.errorMessage = err
    });
  }

  // Method to delete an asset
  deleteAsset(assetId: number): void {
    if (confirm('Are you sure you want to delete this asset?')) {
      this.assetService.deleteAsset(assetId).subscribe({
        next: () => {
          this.successMessage = 'Asset deleted successfully!';
          this.fetchAllAssets();
        },
        error: (err) => this.errorMessage = err
      });
    }
  }

  // Method to enable editing mode
  editAsset(asset: Asset): void {
    this.editingAsset = { ...asset };
  }

  // Method to update an asset
  updateAsset(form: any): void {
    if (form.invalid || !this.editingAsset) {
      return;
    }
    this.assetService.updateAsset(this.editingAsset.assetId, this.editingAsset).subscribe({
      next: (data) => {
        this.successMessage = 'Asset updated successfully!';
        this.fetchAllAssets();
        this.editingAsset = null;
      },
      error: (err) => this.errorMessage = err
    });
  }

  // Method to cancel editing
  cancelEdit(): void {
    this.editingAsset = null;
  }
}
