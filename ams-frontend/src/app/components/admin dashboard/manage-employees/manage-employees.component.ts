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
  roles: Role[] = []; 
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

  fetchAllEmployees(): void {
    this.employeeService.getAllEmployee().subscribe({
      next: (data) => this.employees = data,
      error: (err) => this.errorMessage = 'Failed to load employees.'
    });
  }

  fetchAllRoles(): void {
    this.roleService.getAllRoles().subscribe({
      next: (data) => this.roles = data,
      error: (err) => this.errorMessage = 'Failed to load roles.'
    });
  }

  addEmployee(form: any): void {
    if (form.invalid) {
      return;
    }

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

  editEmployee(employee: Employee): void {
    this.editingEmployee = { ...employee };
  }

  updateEmployee(): void {
    if (!this.editingEmployee) {
      return;
  }

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

  cancelEdit(): void {
    this.editingEmployee = null;
  }
}
