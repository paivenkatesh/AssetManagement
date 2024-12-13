import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  /**
   * Determines if a route can be activated based on user roles.
   * @param route The activated route snapshot.
   * @returns True if user has required roles, else redirects to unauthorized.
   */
  canActivate(route: ActivatedRouteSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const expectedRoles: string[] = route.data['roles'];
    const userRoles = this.authService.getUserRoles();

    const hasRole = expectedRoles.some(role => userRoles.includes(role));

    if (hasRole) {
      return true;
    } else {
      return this.router.parseUrl('/unauthorized');
    }
  }

}