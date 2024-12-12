import { Employee } from './employee.model';
import { Asset } from './asset.model';

export enum BorrowingStatus {
  Active = 'Active',
  Returned = 'Returned'
}

export interface AssetBorrowing {
  borrowingId: number;
  employee: Employee;
  asset: Asset;
  borrowedAt: string; 
  returnedAt: string; 
  status: BorrowingStatus;
}