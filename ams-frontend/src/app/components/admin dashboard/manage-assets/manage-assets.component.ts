import { Component, OnInit } from '@angular/core';
import { AssetService } from '../../../services/asset.service';
import { Asset, AssetStatus } from '../../../models/asset.model';
import { AssetCategory } from '../../../models/asset-category.model';
import { AssetCategoryService } from '../../../services/asset-category.service';

@Component({
  selector: 'app-manage-assets',
  templateUrl: './manage-assets.component.html',
  styleUrls: ['./manage-assets.component.css']
})
export class ManageAssetsComponent implements OnInit {
  assets: Asset[] = [];
  categories: AssetCategory[] = []; // To store fetched categories
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

  constructor(
    private assetService: AssetService,
    private assetCategoryService: AssetCategoryService
  ) { }

  ngOnInit(): void {
    this.fetchAllAssets();
    this.fetchAllCategories();
  }

  /**
   * Fetches all assets from the backend.
   */
  fetchAllAssets(): void {
    this.assetService.getAllAssets().subscribe({
      next: (data) => this.assets = data,
      error: (err) => this.errorMessage = 'Failed to load assets.'
    });
  }

  /**
   * Fetches all asset categories.
   */
  fetchAllCategories(): void {
    this.assetCategoryService.getAllCategories().subscribe({
      next: (data) => this.categories = data,
      error: (err) => this.errorMessage = 'Failed to load asset categories.'
    });
  }

  /**
   * Adds a new asset.
   * @param form The form containing asset details.
   */
  addAsset(form: any): void {
    if (form.invalid) {
      return;
    }

    const categoryId = +this.newAsset.category.categoryId; 
    const selectedCategory = this.categories.find(cat => cat.categoryId === categoryId);

    if (!selectedCategory) {
      this.errorMessage = 'Invalid category selected.';
      return;
    }

    const assetToAdd: Asset = {
      ...this.newAsset,
      category: selectedCategory
    };

    this.assetService.addAsset(assetToAdd).subscribe({
      next: (data) => {
        this.successMessage = 'Asset added successfully!';
        this.fetchAllAssets();
        form.resetForm();
        // Reset newAsset with default values
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
      error: (err) => this.errorMessage = 'Failed to add asset.'
    });
  }

  /**
   * Deletes an asset if it's available.
   * @param assetId The ID of the asset to delete.
   */
  deleteAsset(assetId: number): void {
    const asset = this.assets.find(a => a.assetId === assetId);
    if (asset && asset.status !== AssetStatus.Available) {
      this.errorMessage = 'Cannot delete asset that is currently assigned or in service.';
      return;
    }

    if (confirm('Are you sure you want to delete this asset?')) {
      this.assetService.deleteAsset(assetId).subscribe({
        next: () => {
          this.successMessage = 'Asset deleted successfully!';
          this.fetchAllAssets();
        },
        error: (err) => this.errorMessage = 'Failed to delete asset.'
      });
    }
  }

  /**
   * Enables editing mode for an asset.
   * @param asset The asset to edit.
   */
  editAsset(asset: Asset): void {
    this.editingAsset = { ...asset };
  }

  /**
   * Updates an existing asset.
   */
  updateAsset(): void {
    const categoryId = +this.editingAsset!.category.categoryId;

    const selectedCategory = this.categories.find(cat => cat.categoryId === categoryId);

    if (!selectedCategory) {
        this.errorMessage = 'Invalid category selected.';
        return;
    }

    const assetToUpdate: Asset = {
        ...this.editingAsset!,
        category: selectedCategory
    };

    this.assetService.updateAsset(assetToUpdate.assetId, assetToUpdate).subscribe({
        next: () => {
            this.successMessage = 'Asset updated successfully!';
            this.fetchAllAssets();
            this.editingAsset = null;
        },
        error: (err) => this.errorMessage = 'Failed to update asset: ' + err.message
    });
}

  cancelEdit(): void {
    this.editingAsset = null;
  }
}