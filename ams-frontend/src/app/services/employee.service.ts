import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Employee } from '../models/employee.model';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = `${environment.apiBaseUrl}/ams/employee`;

  constructor(private http: HttpClient) { }

  getEmployeeById(employeeId: number): Observable<Employee> {
    const url = `${this.apiUrl}/getEmployeeById/${employeeId}`;
    return this.http.get<Employee>(url);
  }

  updateEmployee(employeeId: number, employeeDetails: Employee): Observable<Employee> {
    const url = `${this.apiUrl}/updateEmployee/${employeeId}`;
    return this.http.put<Employee>(url, employeeDetails);
  }

  deleteEmployee(employeeId: number): Observable<void> {
    const url = `${this.apiUrl}/delete/${employeeId}`;
    return this.http.delete<void>(url);
  }

  getAllEmployee(): Observable<Employee[]> {
    const url = `${this.apiUrl}/getAllEmployee`;
    return this.http.get<Employee[]>(url);
  }

  addEmployee(employee: Employee): Observable<Employee> {
    const url = 'http://localhost:8080/api/auth/register'; 
    return this.http.post<Employee>(url, employee);
  }
}
