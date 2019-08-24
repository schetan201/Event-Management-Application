import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder} from '@angular/forms';
import { EventBookingService } from './event-booking.service';
import { ActivatedRoute, Router } from '@angular/router';
import { isNumber } from 'util';
import { UserService } from '../shared/user.service';
// import { HomeService } from '..home.service';


@Component({
  selector: 'app-edit-event',
  templateUrl: './event-booking.component.html',
  styleUrls: ['./event-booking.component.scss']
})
export class EventBookingComponent implements OnInit {

  formVar: FormGroup;
  objectEvent: any;
  id: '';

  eventObj = {

    eventId : 0,
    eventName: '',
    eventDetails: '',
    eventContact: '',
    eventVenue: '',
    eventDate: '',
    userId: 0
};

bookEventObj = {
  eventId : 0,
  userId: 0
};
  constructor(private fb: FormBuilder, private eventBookingService: EventBookingService,
     private router: ActivatedRoute, private userService: UserService, private route: Router) {

    this.router.queryParams.subscribe(params => {
this.eventObj.eventId = params['eventId'];
this.eventObj.eventName = params['eventName'];
this.eventObj.eventDetails = params['eventDetails'];
this.eventObj.eventContact = params['eventContact'];
this.eventObj.eventVenue = params['eventVenue'];
this.eventObj.eventDate = params['eventDate'];
this.eventObj.userId = Number(this.userService.getUserId());
    });
  }

  ngOnInit() {
  }

onSubmit() {

  if (confirm('Confirm Ticket Booking?')) {
    this.bookEventObj.eventId = Number(this.eventObj.eventId);
    this.bookEventObj.userId = Number(this.eventObj.userId);
    this.eventBookingService.bookEvent(this.bookEventObj).subscribe(msg => {
      if (msg === true) {
        alert('Your Ticket has been Booked successfully!');
        this.route.navigate(['/userProfile']);
      } else {
        alert('Something went wrong! Please try again!');
        this.route.navigate(['/home']);
      }

    });
  }

}
}
