import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Role } from '../models/role.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private roles: Role[] = [
    { roleId: 1, roleName: 'ROLE_ADMIN' },
    { roleId: 2, roleName: 'ROLE_USER' }
  ];

  constructor() { }

  /**
   * Retrieves all roles.
   * @returns Observable of Role array.
   */
  getAllRoles(): Observable<Role[]> {
    // Replace with HTTP GET request when backend endpoints are available
    return of(this.roles);
  }
}
