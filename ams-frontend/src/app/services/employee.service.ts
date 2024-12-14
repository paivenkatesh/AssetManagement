import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Employee } from '../models/employee.model';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
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

  /**
   * Update an existing employee's details.
   * @param employeeId - ID of the employee to be updated.
   * @param employeeDetails - EmployeeDto object containing updated details.
   * @returns Observable of the updated Employee.
   */
  updateEmployee(employeeId: number, employeeDetails: Employee): Observable<Employee> {
    const url = `${this.apiUrl}/updateEmployee/${employeeId}`;
    return this.http.put<Employee>(url, employeeDetails);
  }

  /**
   * Delete an employee by their ID.
   * @param employeeId - ID of the employee to be deleted.
   * @returns Observable of void.
   */
  deleteEmployee(employeeId: number): Observable<void> {
    const url = `${this.apiUrl}/delete/${employeeId}`;
    return this.http.delete<void>(url);
  }

  /**
   * Retrieve all employees.
   * @returns Observable of an array of Employee.
   */
  getAllEmployee(): Observable<Employee[]> {
    const url = `${this.apiUrl}/getAllEmployee`;
    return this.http.get<Employee[]>(url);
  }

/**
   * Add a new employee.
   * @param employee - Employee object to be added.
   * @returns Observable of the created Employee.
   */
  addEmployee(employee: Employee): Observable<Employee> {
    const url = 'http://localhost:8080/api/auth/register'; 
    return this.http.post<Employee>(url, employee);
  }
}
