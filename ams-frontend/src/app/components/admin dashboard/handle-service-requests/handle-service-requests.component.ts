import { Component, OnInit } from '@angular/core';
import { ServiceRequestService } from '../../../services/service-request.service';
import { ServiceRequest, Status } from '../../../models/service-request.model';
import { Employee } from '../../../models/employee.model';
import { Asset } from '../../../models/asset.model';

@Component({
  selector: 'app-handle-service-requests',
  templateUrl: './handle-service-requests.component.html',
  styleUrls: ['./handle-service-requests.component.css']
})
export class HandleServiceRequestsComponent implements OnInit {
  serviceRequests: ServiceRequest[] = [];
  successMessage: string = '';
  errorMessage: string = '';
  editingRequest: ServiceRequest | null = null;

  // Filter
  filterStatus: Status | '' = '';

  constructor(private serviceRequestService: ServiceRequestService) { }

  ngOnInit(): void {
    this.fetchServiceRequests();
  }
// method to fetch all service requests
  fetchServiceRequests(): void {
    this.serviceRequestService.getAllServiceRequests().subscribe({
      next: (data) => this.serviceRequests = data,
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Failed to load service requests.';
      }
    });
  }


  updateServiceRequestStatus(request: ServiceRequest, newStatus: Status): void {
    this.serviceRequestService.updateServiceRequest(request.serviceRequestId, newStatus).subscribe({
      next: (updatedRequest) => {
        this.successMessage = 'Service request status updated successfully!';
        this.fetchServiceRequests();
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Failed to update service request status.';
      }
    });
  }

  // edit service requests
  editServiceRequest(request: ServiceRequest): void {
    this.editingRequest = { ...request };
  }

  // update service requests
  saveServiceRequest(): void {
    if (this.editingRequest) {
      this.serviceRequestService.updateServiceRequest(this.editingRequest.serviceRequestId, this.editingRequest.status).subscribe({
        next: () => {
          this.successMessage = 'Service request updated successfully!';
          this.fetchServiceRequests();
          this.editingRequest = null;
        },
        error: (err) => {
          console.error(err);
          this.errorMessage = 'Failed to update service request.';
        }
      });
    }
  }

  cancelEdit(): void {
    this.editingRequest = null;
  }
  // method to fetch service requests by status
  filterServiceRequests(): void {
    if (this.filterStatus) {
      this.serviceRequestService.findByStatus(this.filterStatus).subscribe({
        next: (data) => this.serviceRequests = data,
        error: (err) => {
          console.error(err);
          this.errorMessage = 'Failed to filter service requests.';
        }
      });
    } else {
      this.fetchServiceRequests();
    }
  }
}
