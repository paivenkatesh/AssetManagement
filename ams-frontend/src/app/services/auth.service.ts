import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs'; 
import { AuthenticationRequest } from '../models/authentication-request.model';
import { AuthenticationResponse } from '../models/authentication-response.model';
import { RegisterRequest } from '../models/register-request.model';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';

interface DecodedToken {
  sub: string;
  employeeId: number;
  name: string;
  role: string[];
  exp: number;
  iat: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.apiBaseUrl}/api/auth`;
  private jwtHelper = new JwtHelperService();

  constructor(private http: HttpClient, private router: Router) { }

  register(registerRequest: RegisterRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/register`, registerRequest, { responseType: 'text' });
  }

  authenticate(authenticationRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrl}/authenticate`, authenticationRequest)
      .pipe(
        tap(response => {
          this.setToken(response.token);
          console.log("Token set in local storage:", response.token); 
        })
      );
  }


  isLoggedIn(): boolean {
    const token = this.getToken();
    return token !== null && !this.jwtHelper.isTokenExpired(token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  removeToken(): void {
    localStorage.removeItem('token');
  }

  logout(): void {
    this.removeToken();
    this.router.navigate(['/login']);
  }

  decodeToken(): DecodedToken | null {
    const token = this.getToken();
    if (!token || this.jwtHelper.isTokenExpired(token)) return null;

    try {
      return this.jwtHelper.decodeToken<DecodedToken>(token);
    } catch (error) {
      console.error('Invalid token', error);
      return null;
    }
  }

  getEmployeeId(): number | null {
    const decoded = this.decodeToken();
    return decoded ? decoded.employeeId : null;
  }

  getUserRoles(): string[] {
    const decoded = this.decodeToken();
    if (!decoded) {
      return [];
    }
    const roles = decoded.role;

    if (Array.isArray(roles)) {
      return roles;
    } else if (typeof roles === 'string') {
      return [roles];
    } else {
      return [];
    }
  }
}