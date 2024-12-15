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

  getAllRoles(): Observable<Role[]> {
    return of(this.roles);
  }
}
