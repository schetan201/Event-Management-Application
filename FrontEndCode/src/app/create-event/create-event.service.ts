import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

// tslint:disable-next-line:class-name
export interface eventInterface {
  evenName: string;
  eventDetails: string;
  eventContact: number;
  eventVenue: string;
  eventDate: Date;
  userId: number;
}

@Injectable({
  providedIn: 'root'
})

export class CreateEventService {
  uri = 'http://localhost:4000';
  noAuthHeader = { headers: new HttpHeaders({ 'Content-Type' : 'application/json' }) };
  constructor(private http: HttpClient) { }
  addEvents(events) {
    return this.http.post(`/final/addEvent`, events, this.noAuthHeader).pipe(map(user => {
      return user;
  }));
  }
}
