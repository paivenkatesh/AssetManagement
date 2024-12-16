import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceRequest, Status } from '../models/service-request.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceRequestService {
  private apiUrl = `${environment.apiBaseUrl}/ams/servicerequest`;

  constructor(private http: HttpClient) { }

  createServiceRequest(employeeId: number, assetId: number, issueTypeId: number, description: string): Observable<ServiceRequest> {
    const encodedDescription = encodeURIComponent(description);
    const url = `${this.apiUrl}/createServiceRequest/${employeeId}/${assetId}/${issueTypeId}?description=${encodedDescription}`;
    return this.http.post<ServiceRequest>(url, {});
  }

  getServiceRequestById(serviceRequestId: number): Observable<ServiceRequest> {
    const url = `${this.apiUrl}/getServiceRequestById/${serviceRequestId}`;
    return this.http.get<ServiceRequest>(url);
  }

  updateServiceRequest(serviceRequestId: number, status: Status): Observable<ServiceRequest> {
    const url = `${this.apiUrl}/updateServiceRequest/${serviceRequestId}/${status}`;
    return this.http.put<ServiceRequest>(url, {});
  }

  getServiceRequestsByEmployee(employeeId: number): Observable<ServiceRequest[]> {
    const url = `${this.apiUrl}/serviceRequestByEmployee/${employeeId}`;
    return this.http.get<ServiceRequest[]>(url);
  }

  getAllServiceRequests(): Observable<ServiceRequest[]> {
    const url = `${this.apiUrl}/allServiceRequests`;
    return this.http.get<ServiceRequest[]>(url);
  }

  findByStatus(status: Status): Observable<ServiceRequest[]> {
    const url = `${this.apiUrl}/findByStatus/${status}`;
    return this.http.get<ServiceRequest[]>(url);
  }
}

