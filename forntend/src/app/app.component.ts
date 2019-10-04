import { Component } from '@angular/core';
//import { ThumbnailComponent } from './modules/movie/components/thumbnail/thumbnail.component';
import {LoginComponent } from './modules/login/login.component';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Movie Cruiser Application';

  logout() {
    localStorage.removeItem('userId');
    localStorage.removeItem('bearerToken');
  }
}
