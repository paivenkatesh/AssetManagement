import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Asset } from '../models/asset.model';
import { AssetCategory } from '../models/asset-category.model';


@Injectable({
  providedIn: 'root'
})
export class AssetService {
  private assetsUrl = 'http://localhost:8080/api/assets';

  constructor(private http: HttpClient) { }

  addAsset(assetDto: Asset): Observable<Asset> {
    return this.http.post<Asset>(`${this.assetsUrl}/add`, assetDto);
  }

  updateAsset(assetId: number, assetDto: Asset): Observable<Asset> {
    return this.http.put<Asset>(`${this.assetsUrl}/update/${assetId}`, assetDto);
  }

  getAssetById(assetId: number): Observable<Asset> {
    return this.http.get<Asset>(`${this.assetsUrl}/getbyid/${assetId}`);
  }

  getAllAssets(): Observable<Asset[]> {
    return this.http.get<Asset[]>(`${this.assetsUrl}/getall`);
  }

  getAssetsByCategory(category: AssetCategory): Observable<Asset[]> {
    return this.http.get<Asset[]>(`${this.assetsUrl}/category`, { params: { categoryName: category.categoryName } });
  }

  deleteAsset(assetId: number): Observable<void> {
    return this.http.delete<void>(`${this.assetsUrl}/delete/${assetId}`);
  }
}
