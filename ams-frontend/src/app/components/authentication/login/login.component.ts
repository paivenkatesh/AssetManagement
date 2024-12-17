import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { AuthenticationRequest } from 'src/app/models/authentication-request.model';
import { AuthenticationResponse } from 'src/app/models/authentication-response.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  authenticationRequest: AuthenticationRequest = {
    email: '',
    password: ''
  };

  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  onLogin(form: any): void {
    if (form.invalid) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.authenticate(this.authenticationRequest).subscribe({
      next: (response: AuthenticationResponse) => {
        this.authService.setToken(response.token);
        this.isLoading = false;

        // Determines redirect based on user role
        const roles = this.authService.getUserRoles();
        if (roles.includes('ROLE_ADMIN')) {
          this.router.navigate(['/admin-dashboard']);
        } else if (roles.includes('ROLE_USER')) {
          this.router.navigate(['/employee-dashboard']);
        } else {
          this.errorMessage = 'Invalid user role.';
        }
      },
      error: (err) => {
        this.errorMessage = err;
        this.isLoading = false;
      }
    });
  }
}