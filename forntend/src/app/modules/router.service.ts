import { Injectable } from '@angular/core';

import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable()
export class RouterService {

  constructor(private router: Router, private location: Location) { }

  routeToPopularMovies() {
    this.router.navigate(['movies']);
  }

  routeToLogin() {
    this.router.navigate(['login']);
  }
  logout() {
    localStorage.removeItem('userId');
    localStorage.removeItem('bearerToken');
    this.routeToLogin();
  }
  routeBack() {
    this.location.back();
  }
}
