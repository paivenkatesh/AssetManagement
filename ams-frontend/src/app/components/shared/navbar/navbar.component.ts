import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(public authService: AuthService, private router: Router) { }

  /**
   * Initiates the logout process with confirmation.
   */
  logout(): void {
    const confirmation = confirm('Are you sure you want to logout?');
    if (confirmation) {
      this.authService.logout();
    }
  }
}
