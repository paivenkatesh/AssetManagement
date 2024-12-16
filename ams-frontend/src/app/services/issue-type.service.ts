import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IssueType } from '../models/issue-type.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IssueTypeService {

  private issueTypeServiceUrl = `${environment.apiBaseUrl}/ams/IssueType`
  constructor(private http: HttpClient) { }

  addIssueType(issueTypeDto: IssueType): Observable<IssueType> {

    return this.http.post<IssueType>(`${this.issueTypeServiceUrl}/addIssueType`, issueTypeDto);
  }

  getIssueTypeById(issueTypeId: number): Observable<IssueType> {

    return this.http.get<IssueType>(`${this.issueTypeServiceUrl}/issueTypeById/${issueTypeId}`);
  }

  getAllIssueTypes(): Observable<IssueType[]> {

    return this.http.get<IssueType[]>(`${this.issueTypeServiceUrl}/allIssueTypes`);
  }

  updateIssueType(issueTypeId: number, issueTypeDetailsDto: IssueType) {

    return this.http.put<IssueType>(`${this.issueTypeServiceUrl}/updateIssueType/${issueTypeId}`, issueTypeDetailsDto);
  }

  deleteIssueType(issueTypeId: number): Observable<void> {

    return this.http.delete<void>(`${this.issueTypeServiceUrl}/delete/${issueTypeId}`);
  }
}
