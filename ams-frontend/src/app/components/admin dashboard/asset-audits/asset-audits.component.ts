import { Component, OnInit } from '@angular/core';
import { AssetAuditService } from '../../../services/asset-audit.service';
import { AssetAudit, AuditStatus } from '../../../models/asset-audit.model';
import { Employee, Gender } from '../../../models/employee.model';
import { Asset, AssetStatus } from '../../../models/asset.model';

@Component({
  selector: 'app-asset-audits',
  templateUrl: './asset-audits.component.html',
  styleUrls: ['./asset-audits.component.css']
})
export class AssetAuditsComponent implements OnInit {
  audits: AssetAudit[] = [];
  employees: Employee[] = []; 
  assets: Asset[] = []; 
  newAudit: AssetAudit = {
    auditId: 0,
    employee: { employeeId: 0, name: '', gender: Gender.Male, contactNumber: '', address: '', email: '', password: '', role: { roleId: 0, roleName: '' } },
    asset: { assetId: 0, assetName: '', category: { categoryId: 0, categoryName: '' }, assetModel: '', manufacturingDate: '', expiryDate: '', assetValue: 0, status: AssetStatus.Available },
    auditStatus: AuditStatus.Pending,
    requestedAt: '',
    updatedAt: ''
  };
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private assetAuditService: AssetAuditService) { }

  ngOnInit(): void {
    this.fetchAllAudits();
  }

  // fetch all audits
  fetchAllAudits(): void {
    this.assetAuditService.getAllAudits().subscribe({
      next: (data) => this.audits = data,
      error: (err) => this.errorMessage = err
    });
  }

  // Method to create a new audit request
  createAuditRequest(form: any): void {
    if (form.invalid) {
      return;
    }
    const auditPayload = {
      employee: { employeeId: this.newAudit.employee.employeeId } as Employee,
      asset: { assetId: this.newAudit.asset.assetId } as Asset,
      auditStatus: AuditStatus.Pending
    };
    this.assetAuditService.sendAuditRequest(auditPayload.employee.employeeId, auditPayload.asset.assetId).subscribe({
      next: (data) => {
        this.successMessage = 'Audit request created successfully!';
        this.fetchAllAudits();
        form.resetForm();
      },
      error: (err) => this.errorMessage = err
    });
  }

  // Method to update audit status
  updateAuditStatus(audit: AssetAudit, newStatus: AuditStatus): void {
    this.assetAuditService.updateAuditStatus(audit.auditId, newStatus).subscribe({
      next: (data) => {
        this.successMessage = 'Audit status updated successfully!';
        this.fetchAllAudits();
      },
      error: (err) => this.errorMessage = err
    });
  }
}
