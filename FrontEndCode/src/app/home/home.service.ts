import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Http, Response, RequestOptions, Headers} from '@angular/http';
import { httpFactory } from '@angular/http/src/http_module';
import { HttpResponse, HttpRequest, HttpHeaders } from '@angular/common/http';
//import 'rxjs/add/operator/map';
//    import 'rxjs/add/operator/catch';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
uri = 'http://localhost:8080';
  constructor(private http: Http) { }
  getEvents() {
    return this.http.get(`/final/getEvents`);
  }

  getEventById(id) {
   return this.http.get(`${this.uri}/event/${id}`);
  }
}
