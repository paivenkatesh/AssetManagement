import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { Employee, Gender } from '../../../models/employee.model';
import { Role } from '../../../models/role.model';
import { RoleService } from '../../../services/role.service';

@Component({
  selector: 'app-manage-employees',
  templateUrl: './manage-employees.component.html',
  styleUrls: ['./manage-employees.component.css']
})
export class ManageEmployeesComponent implements OnInit {
  employees: Employee[] = [];
  roles: Role[] = []; // To store fetched roles
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

  constructor(
    private employeeService: EmployeeService,
    private roleService: RoleService
  ) { }

  ngOnInit(): void {
    this.fetchAllEmployees();
    this.fetchAllRoles();
  }

  /**
   * Fetches all employees from the backend.
   */
  fetchAllEmployees(): void {
    this.employeeService.getAllEmployee().subscribe({
      next: (data) => this.employees = data,
      error: (err) => this.errorMessage = 'Failed to load employees.'
    });
  }

  /**
   * Fetches all roles.
   */
  fetchAllRoles(): void {
    this.roleService.getAllRoles().subscribe({
      next: (data) => this.roles = data,
      error: (err) => this.errorMessage = 'Failed to load roles.'
    });
  }

  /**
   * Adds a new employee.
   * @param form The form containing employee details.
   */
  addEmployee(form: any): void {
    if (form.invalid) {
      return;
    }

    // Find the selected role from the roles array
    const selectedRole = this.roles.find(role => role.roleId === this.newEmployee.role.roleId);

    if (!selectedRole) {
      this.errorMessage = 'Invalid role selected.';
      return;
    }

    const employeeToAdd: Employee = {
      ...this.newEmployee,
      role: selectedRole
    };

    this.employeeService.addEmployee(employeeToAdd).subscribe({
      next: (data) => {
        this.successMessage = 'Employee added successfully!';
        this.fetchAllEmployees();
        form.resetForm();
        // Reset newEmployee with default values
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
      error: (err) => this.errorMessage = 'Failed to add employee.'
    });
  }

  /**
   * Deletes an employee.
   * @param employeeId The ID of the employee to delete.
   */
  deleteEmployee(employeeId: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(employeeId).subscribe({
        next: () => {
          this.successMessage = 'Employee deleted successfully!';
          this.fetchAllEmployees();
        },
        error: (err) => this.errorMessage = 'Failed to delete employee.'
      });
    }
  }

  /**
   * Enables editing mode for an employee.
   * @param employee The employee to edit.
   */
  editEmployee(employee: Employee): void {
    this.editingEmployee = { ...employee };
  }

  /**
   * Updates an existing employee.
   * @param form The form containing updated employee details.
   */
  updateEmployee(form: any): void {
    if (form.invalid || !this.editingEmployee) {
      return;
    }

    // Find the selected role from the roles array
    const selectedRole = this.roles.find(role => role.roleId === this.editingEmployee?.role.roleId);

    if (!selectedRole) {
      this.errorMessage = 'Invalid role selected.';
      return;
    }

    const employeeToUpdate: Employee = {
      ...this.editingEmployee,
      role: selectedRole
    };

    this.employeeService.updateEmployee(employeeToUpdate.employeeId, employeeToUpdate).subscribe({
      next: (data) => {
        this.successMessage = 'Employee updated successfully!';
        this.fetchAllEmployees();
        this.editingEmployee = null;
      },
      error: (err) => this.errorMessage = 'Failed to update employee.'
    });
  }

  /**
   * Cancels the editing mode.
   */
  cancelEdit(): void {
    this.editingEmployee = null;
  }
}
