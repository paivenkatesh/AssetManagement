import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AssetAudit, AuditStatus } from '../models/asset-audit.model';

@Injectable({
  providedIn: 'root'
})
export class AssetAuditService {
  private auditsUrl = 'http://localhost:8080/api/audits';

  constructor(private http: HttpClient) { }

  sendAuditRequest(employeeId: number, assetId: number): Observable<AssetAudit> {
    return this.http.post<AssetAudit>(`${this.auditsUrl}/send/${employeeId}/${assetId}`, {});
  }

  updateAuditStatus(auditId: number, auditStatus: AuditStatus): Observable<AssetAudit> {
    return this.http.put<AssetAudit>(`${this.auditsUrl}/update/${auditId}/${auditStatus}`, {});
  }

  getAuditsByEmployee(employeeId: number): Observable<AssetAudit[]> {
    return this.http.get<AssetAudit[]>(`${this.auditsUrl}/getbyeid/${employeeId}`);
  }

  getAllAudits(): Observable<AssetAudit[]> {
    return this.http.get<AssetAudit[]>(`${this.auditsUrl}/getall`);
  }

  getAuditById(auditId: number): Observable<AssetAudit> {
    return this.http.get<AssetAudit>(`${this.auditsUrl}/getbyid/${auditId}`);
  }
}
