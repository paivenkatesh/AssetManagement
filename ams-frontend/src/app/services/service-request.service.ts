import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ServiceRequest, Status } from '../models/service-request.model';

@Injectable({
  providedIn: 'root'
})
export class ServiceRequestService {
  private apiUrl = 'http://localhost:8080/ams/servicerequest';

  constructor(private http: HttpClient) { }

  /**
   * Create a new service request.
   * @param employeeId - ID of the employee making the request.
   * @param assetId - ID of the asset.
   * @param issueTypeId - ID of the issue type.
   * @param description - Description of the issue.
   * @returns Observable of the created ServiceRequest.
   */
  createServiceRequest(employeeId: number, assetId: number, issueTypeId: number, description: string): Observable<ServiceRequest> {
    const url = `${this.apiUrl}/createServiceRequest/${employeeId}/${assetId}/${issueTypeId}`;
    return this.http.post<ServiceRequest>(url, { description });
  }

  /**
   * Retrieve a service request by its ID.
   * @param serviceRequestId - ID of the service request.
   * @returns Observable of the ServiceRequest.
   */
  getServiceRequestById(serviceRequestId: number): Observable<ServiceRequest> {
    const url = `${this.apiUrl}/getServiceRequestById/${serviceRequestId}`;
    return this.http.get<ServiceRequest>(url);
  }

  /**
   * Update the status of a service request.
   * @param serviceRequestId - ID of the service request.
   * @param status - New status of the service request.
   * @returns Observable of the updated ServiceRequest.
   */
  updateServiceRequest(serviceRequestId: number, status: Status): Observable<ServiceRequest> {
    const url = `${this.apiUrl}/updateServiceRequest/${serviceRequestId}/${status}`;
    return this.http.put<ServiceRequest>(url, {});
  }

  /**
   * Retrieve all service requests for a specific employee.
   * @param employeeId - ID of the employee.
   * @returns Observable of an array of ServiceRequest.
   */
  getServiceRequestsByEmployee(employeeId: number): Observable<ServiceRequest[]> {
    const url = `${this.apiUrl}/serviceRequestByEmployee/${employeeId}`;
    return this.http.get<ServiceRequest[]>(url);
  }

  /**
   * Retrieve all service requests.
   * @returns Observable of an array of ServiceRequest.
   */
  getAllServiceRequests(): Observable<ServiceRequest[]> {
    const url = `${this.apiUrl}/allServiceRequests`;
    return this.http.get<ServiceRequest[]>(url);
  }

  /**
   * Find service requests by their status.
   * @param status - Status to filter service requests.
   * @returns Observable of an array of ServiceRequest.
   */
  findByStatus(status: Status): Observable<ServiceRequest[]> {
    const url = `${this.apiUrl}/findByStatus/${status}`;
    return this.http.get<ServiceRequest[]>(url);
  }
}