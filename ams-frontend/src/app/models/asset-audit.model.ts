import { Employee } from './employee.model';
import { Asset } from './asset.model';

export enum AuditStatus {
  Pending = 'Pending',
  Verified = 'Verified',
  Rejected = 'Rejected'
}

export interface AssetAudit {
  auditId: number;
  employee: Employee;
  asset: Asset;
  auditStatus: AuditStatus;
  requestedAt: string; 
  updatedAt: string; 
}