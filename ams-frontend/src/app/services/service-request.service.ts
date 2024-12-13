import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceRequest, Status } from '../models/service-request.model';

@Injectable({
  providedIn: 'root'
})
export class ServiceRequestService {

  private serviceRequestUrl = 'http://localhost:8080/ams/servicerequest'

  constructor(private http: HttpClient) { }

  createServiceRequest(employeeId: number, assetId: number, issueTypeId: number, description: string): Observable<ServiceRequest> {

    return this.http.post<ServiceRequest>(`${this.serviceRequestUrl}/createServiceRequest/${employeeId}/${assetId}/${issueTypeId}`, description);
  }

  getServiceRequestId(serviceRequestId: number): Observable<ServiceRequest> {

    return this.http.get<ServiceRequest>(`${this.serviceRequestUrl}/getServiceRequestById/${serviceRequestId}`);
  }

  /*
  //facing error with put. Error is Type 'Observable<ArrayBuffer>' is not assignable to type 'Observable<ServiceRequest>'.
  updateServiceRequest(serviceRequestId: number, status: Status): Observable<ServiceRequest> {
    return this.http.put<ServiceRequest>(`${this.serviceRequestUrl}/updateServiceRequest/${serviceRequestId}/${status}`);
  }
  */

  getServiceRequestsByEmployee(employeeId: number): Observable<ServiceRequest[]> {
    return this.http.get<ServiceRequest[]>(`${this.serviceRequestUrl}/serviceRequestByEmployee/${employeeId}`);
  }

  getAllServiceRequests(): Observable<ServiceRequest[]> {

    return this.http.get<ServiceRequest[]>(`${this.serviceRequestUrl}/allServiceRequests`);
  }
  
  findByStatus(status: Status): Observable<ServiceRequest[]> {
    
    return this.http.get<ServiceRequest[]>(`${this.serviceRequestUrl}/findByStatus/${status}`);
  }



}
