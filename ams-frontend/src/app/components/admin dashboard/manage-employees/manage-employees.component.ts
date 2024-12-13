import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { Employee, Gender } from '../../../models/employee.model';
import { Role } from '../../../models/role.model';

@Component({
  selector: 'app-manage-employees',
  templateUrl: './manage-employees.component.html',
  styleUrls: ['./manage-employees.component.css']
})
export class ManageEmployeesComponent implements OnInit {
  employees: Employee[] = [];
  roles: Role[] = []; // Assuming you have a RoleService to fetch roles
  newEmployee: Employee = {
    employeeId: 0,
    name: '',
    gender: Gender.Male,
    contactNumber: '',
    address: '',
    email: '',
    password: '',
    role: { roleId: 0, roleName: '' }
  };
  successMessage: string = '';
  errorMessage: string = '';
  editingEmployee: Employee | null = null;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.fetchAllEmployees();
    // Fetch roles if needed for selecting in forms
    // Implement RoleService and fetch here
  }

  fetchAllEmployees(): void {
    this.employeeService.getAllEmployee().subscribe({
      next: (data) => this.employees = data,
      error: (err) => this.errorMessage = err
    });
  }

  // Method to add a new employee
  addEmployee(form: any): void {
    if (form.invalid) {
      return;
    }
    this.employeeService.addEmployee(this.newEmployee).subscribe({
      next: (data) => {
        this.successMessage = 'Employee added successfully!';
        this.fetchAllEmployees();
        form.resetForm();
        this.newEmployee = {
          employeeId: 0,
          name: '',
          gender: Gender.Male,
          contactNumber: '',
          address: '',
          email: '',
          password: '',
          role: { roleId: 0, roleName: '' }
        };
      },
      error: (err) => this.errorMessage = err
    });
  }

  // Method to delete an employee
  deleteEmployee(employeeId: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(employeeId).subscribe({
        next: () => {
          this.successMessage = 'Employee deleted successfully!';
          this.fetchAllEmployees();
        },
        error: (err) => this.errorMessage = err
      });
    }
  }

  // Method to enable editing mode
  editEmployee(employee: Employee): void {
    this.editingEmployee = { ...employee };
  }

  // Method to update an employee
  updateEmployee(form: any): void {
    if (form.invalid || !this.editingEmployee) {
      return;
    }
    this.employeeService.updateEmployee(this.editingEmployee.employeeId, this.editingEmployee).subscribe({
      next: (data) => {
        this.successMessage = 'Employee updated successfully!';
        this.fetchAllEmployees();
        this.editingEmployee = null;
      },
      error: (err) => this.errorMessage = err
    });
  }

  // Method to cancel editing
  cancelEdit(): void {
    this.editingEmployee = null;
  }
}
