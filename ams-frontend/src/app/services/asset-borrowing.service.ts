import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AssetBorrowing} from '../models/asset-borrowing.model';

@Injectable({
  providedIn: 'root'
})
export class AssetBorrowingService {
  private borrowingsUrl = 'http://localhost:8080/api/borrowings';

  constructor(private http: HttpClient) { }

  borrowAsset(employeeId: number, assetId: number): Observable<AssetBorrowing> {
    return this.http.post<AssetBorrowing>(`${this.borrowingsUrl}/borrow/${employeeId}/${assetId}`, {});
  }

  returnAsset(borrowingId: number): Observable<AssetBorrowing> {
    return this.http.put<AssetBorrowing>(`${this.borrowingsUrl}/return/${borrowingId}`, {});
  }

  getBorrowingsByEmployee(employeeId: number): Observable<AssetBorrowing[]> {
    return this.http.get<AssetBorrowing[]>(`${this.borrowingsUrl}/getbyeid/${employeeId}`);
  }

  getAllActiveBorrowings(): Observable<AssetBorrowing[]> {
    return this.http.get<AssetBorrowing[]>(`${this.borrowingsUrl}/active`);
  }
}
