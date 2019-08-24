import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  uri = 'http://localhost:8080';
  noAuthHeader = { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*',
  'Content-Type' : 'application/json' }) };
  constructor(private http: HttpClient) { }
  login(loginData) {
    return this.http.post<any>(`/final/login`, loginData, this.noAuthHeader)
        .pipe(map(user => {
            return user;
        }));
}

  getById(id: number) {
      return this.http.get(`${this.uri}/users/` + id);
  }
  setUserId(userId) {
    localStorage.setItem('userId', userId);
  }
  getUserId() {
    return localStorage.getItem('userId');
  }

  deleteEvent(eventId) {
    const hparams = new HttpParams().set('eventId', eventId);
    return this.http.delete(`/final/deleteEvent`, {params: hparams});
  }

  cancelEvent(eventObj) {
    const hparams = new HttpParams().set('eventId', eventObj.eventId).set('userId', eventObj.userId);
    return this.http.delete(`/final/cancelEvent`, {params: hparams});
  }

  getEventForUser(userId) {
    const hparams = new HttpParams().set('userId', userId);
    return this.http.get(`/final/getEventsById`, {params: hparams});
  }

  getBookedEvents(userId) {
    const hparams = new HttpParams().set('userId', userId);
    return this.http.get(`/final/getBookedEvents`, {params: hparams});
  }

editEvent(id) {
  console.log('in before edit service');
  return this.http.delete(`${this.uri}/events/${id}`);
}

  register(userModel) {
      return this.http.post(`/final/addUser`, JSON.stringify(userModel), this.noAuthHeader);
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }
  deleteToken() {
    localStorage.removeItem('token');
  }
  getToken() {
    return localStorage.getItem('token');
  }

  getUserProfile() {
    const hparams = new HttpParams().set('userName', this.getToken());
    return this.http.get(`/final/getUserProfile`, {params: hparams});
  }

  isLoggedIn() {
    if (this.getToken() !== null && this.getToken() !== '') {
      return true;
    } else {
      return false;
    }
  }
}
