import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EventBookingService {
  constructor(private http: HttpClient) { }
  bookEvent(events) {
    return this.http.post(`/final/bookTkt`, events);
  }
}
