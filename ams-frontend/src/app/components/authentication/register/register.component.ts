   // register.component.ts
   import { Component, OnInit } from '@angular/core';
   import { Router } from '@angular/router';
   import { RegisterRequest } from '../../../models/register-request.model';
   import { AuthService } from '../../../services/auth.service';
   import { RoleService } from '../../../services/role.service';
   import { Role } from '../../../models/role.model';
   import { Gender } from '../../../models/employee.model'; 

   @Component({
     selector: 'app-register',
     templateUrl: './register.component.html',
     styleUrls: ['./register.component.css']
   })
   export class RegisterComponent implements OnInit {

     registerRequest: RegisterRequest = {
       name: '',
       gender: Gender.Male, // Set a default value if Gender is an enum
       contactNumber: '',
       address: '',
       email: '',
       password: '',
       roleId: 0// Initialize with a default Role
     };

     confirmPassword: string = '';
     roles: Role[] = [];
     errorMessage: string = '';
     successMessage: string = '';
     isLoading: boolean = false;
     genders = Object.values(Gender);

     constructor(
       private authService: AuthService,
       private roleService: RoleService,
       private router: Router
     ) { }

     ngOnInit(): void {
       this.fetchRoles();
     }

     /**
      * Fetches available roles for selection.
      */
     fetchRoles(): void {
       this.roleService.getAllRoles().subscribe({
         next: (data) => this.roles = data,
         error: (err) => this.errorMessage = 'Failed to load roles.'
       });
     }

     /**
      * Handles the registration form submission.
      * @param form The registration form.
      */
     onRegister(form: any): void {
       if (form.invalid) {
         return;
       }

       // Check if password and confirmPassword match
       if (this.registerRequest.password !== this.confirmPassword) {
         this.errorMessage = 'Passwords do not match.';
         return;
       }

       // Additional validations (optional)
       if (!this.validateContactNumber(this.registerRequest.contactNumber)) {
         this.errorMessage = 'Enter a valid contact number.';
         return;
       }

       this.isLoading = true;
       this.errorMessage = '';
       this.successMessage = '';

       this.authService.register(this.registerRequest).subscribe({
         next: (response: string) => {
           this.successMessage = 'Registration successful! Redirecting to login...';
           this.isLoading = false;
           setTimeout(() => {
             this.router.navigate(['/login']);
           }, 3000);
         },
         error: (err) => {
           // Handle specific error messages from backend if available
           this.errorMessage = err.error || 'Registration failed. Please try again.';
           this.isLoading = false;
         }
       });
     }

     /**
      * Validates the contact number format.
      * @param contactNumber The contact number to validate.
      * @returns True if valid, else false.
      */
     validateContactNumber(contactNumber: string): boolean {
       // Example regex for 10-digit numbers
       const regex = /^\d{10}$/;
       return regex.test(contactNumber);
     }

   }