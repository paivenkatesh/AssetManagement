import { Component, OnInit } from '@angular/core';
import { ServiceRequestService } from '../../../services/service-request.service';
import { IssueTypeService } from '../../../services/issue-type.service';
import { AssetService } from '../../../services/asset.service';
import { Asset } from '../../../models/asset.model';
import { IssueType } from '../../../models/issue-type.model';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-service-request',
  templateUrl: './service-request.component.html',
  styleUrls: ['./service-request.component.css']
})
export class ServiceRequestComponent implements OnInit {
  assets: Asset[] = [];
  issueTypes: IssueType[] = [];
  newServiceRequest: { assetId: number, issueTypeId: number, description: string } = {
    assetId: 0,
    issueTypeId: 0,
    description: ''
  };
  successMessage: string = '';
  errorMessage: string = '';
  employeeId: number | null = null;

  constructor(
    private assetService: AssetService,
    private issueTypeService: IssueTypeService,
    private serviceRequestService: ServiceRequestService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.employeeId = this.authService.getEmployeeId();
    if (this.employeeId !== null) {
      this.fetchAssets();
      this.fetchIssueTypes();
    } else {
      this.errorMessage = 'Invalid employee ID.';
    }
  }

  /**
   * Fetches all assets assigned to the employee.
   */
  fetchAssets(): void {
    this.assetService.getAssetsByEmployee(this.employeeId!).subscribe({
      next: (data) => this.assets = data,
      error: (err) => this.errorMessage = 'Failed to load assets.'
    });
  }

  /**
   * Fetches all available issue types.
   */
  fetchIssueTypes(): void {
    this.issueTypeService.getAllIssueTypes().subscribe({
      next: (data) => this.issueTypes = data,
      error: (err) => this.errorMessage = 'Failed to load issue types.'
    });
  }

  /**
   * Submits a new service request.
   * @param form The form containing service request details.
   */
  submitServiceRequest(form: any): void {
    if (form.invalid) {
      return;
    }

    this.serviceRequestService.createServiceRequest(
      this.employeeId!,
      this.newServiceRequest.assetId,
      this.newServiceRequest.issueTypeId,
      this.newServiceRequest.description
    ).subscribe({
      next: (data) => {
        this.successMessage = 'Service request submitted successfully!';
        form.resetForm();
      },
      error: (err) => this.errorMessage = 'Failed to submit service request.'
    });
  }
}
