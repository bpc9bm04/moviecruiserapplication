import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticateUser(data) {
    return this.httpClient.post('http://localhost:8081/api/v1/userservice/login', data);
  }

  setBearerToken(token) {
    localStorage.setItem('bearerToken', token);
  }

  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

  setUserId(userId) {
    localStorage.setItem('userId', userId);
  }

  getUserId() {
    return localStorage.getItem('userId');
  }
  
  isUserAuthenticated(token): Promise<boolean> {
   // return this.httpClient.post('http://localhost:8765/user-authentication-service/api/v1/auth/isAuthenticated', {}, {
    return this.httpClient.post('http://localhost:8081/api/v1/userservice/isAuthenticated', {}, { 
      headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
    }).map(response => response['isAuthenticated']=='true').toPromise();
  }

  registerUser(data) {
    return this.httpClient.post('http://localhost:8081/api/v1/userservice/register', data);
  }
}
