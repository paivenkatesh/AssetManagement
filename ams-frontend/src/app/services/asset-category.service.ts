import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AssetCategory } from '../models/asset-category.model';

@Injectable({
  providedIn: 'root'
})
export class AssetCategoryService {
  private categories: AssetCategory[] = [
    { categoryId: 1, categoryName: 'Electronics' },
    { categoryId: 2, categoryName: 'Furniture' },
    { categoryId: 3, categoryName: 'Vehicles' }
  ];

  constructor() { }

  /**
   * Retrieves all asset categories.
   * @returns Observable of AssetCategory array.
   */
  getAllCategories(): Observable<AssetCategory[]> {
    // Replace with HTTP GET request when backend endpoints are available
    return of(this.categories);
  }
}
