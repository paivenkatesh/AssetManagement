import { AssetCategory } from './asset-category.model';

export enum AssetStatus {
  Available = 'Available',
  Borrowed = 'Borrowed'
}

export interface Asset {
  assetId: number;
  assetName: string;
  category: AssetCategory;
  assetModel: string;
  manufacturingDate: string; 
  expiryDate: string; 
  assetValue: number;
  status: AssetStatus;
}