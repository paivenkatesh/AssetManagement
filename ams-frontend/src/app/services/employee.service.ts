import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


import { Employee } from '../models/employee.model';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  [x: string]: any;
  private apiUrl = 'http://localhost:8080/ams/employee';

  constructor(private http: HttpClient) { }

  /**
   * Retrieve an employee by their ID.
   * @param employeeId - ID of the employee.
   * @returns Observable of the Employee.
   */
  getEmployeeById(employeeId: number): Observable<Employee> {
    const url = `${this.apiUrl}/getEmployeeById/${employeeId}`;
    return this.http.get<Employee>(url);
  }
}

