import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Employee } from '../models/employee.model';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeeUrl = 'http://localhost:8080/ams/employee';

  constructor(private http: HttpClient) { }

  // Adding a new Employee
  registerEmployee(EmployeeDto: Employee): Observable<Employee> {

    return this.http.post<Employee>(`${this.employeeUrl}/register`, EmployeeDto);
  }


  //Get Employee using employeeId
  getEmployeeById(employeeId: number): Observable<Employee> {

    return this.http.get<Employee>(`${this.employeeUrl}/getEmployeeById/${employeeId}`);
  }
  

  //Update existing employeeDetails
  updateEmployee(employeeId: number, employeeDetailsDto: Employee): Observable<Employee> {

    return this.http.put<Employee>(`${this.employeeUrl}/updateEmployee/${employeeId}`, employeeDetailsDto);
  }


  //Delete Employee using Id
  deleteEmployee(employeeId: number): Observable<void> {

    return this.http.delete<void>(`${this.employeeUrl}/delete/${employeeId}`);
  }


  //Get a list of all Employee
  getAllEmployee():Observable<Employee[]> {

    return this.http.get<Employee[]>(`${this.employeeUrl}/getAllEmployee`);
  }


}
