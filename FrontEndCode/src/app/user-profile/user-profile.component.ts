import { Component, OnInit, Input } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { UserService } from '../shared/user.service';
import { HomeService } from '../home/home.service';
import { EditEventComponent } from '../edit-event/edit-event.component';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  userDetails;
  eventsList;
  bookedEventsList;
  show: boolean;
  eventObj = {
    eventId: 0,
    userId: 0
  };

  @Input() editEventComponent: EditEventComponent;
  constructor( private router: Router,
    private userService: UserService,
    private homeService: HomeService) { }

  ngOnInit() {
    this.userDetails = {
      'firstName' : '',
      'lastName' : ''
    };
    this.userService.getUserProfile().subscribe(
      res => {
        this.userDetails.firstName = res.firstName; // User Details
        this.userDetails.lastName = res.lastName;
      },
      err => {
                console.log(err);
      }
    );

    this.userService.getEventForUser(Number(this.userService.getUserId())).subscribe((event) => {
      if (event === null) {
        this.show = true;
      } else {
        this.eventsList = event;
      }
    });

    this.userService.getBookedEvents(Number(this.userService.getUserId())).subscribe((eventB) => {
      if (eventB === null) {
        this.show = true;
      } else {
        this.bookedEventsList = eventB;
      }
    });
  }

  deleteEvent(event) {
    if (confirm('Do you want to delete this Event?')) {
      this.userService.deleteEvent(Number(event.eventId)).subscribe(updateEvents => {
        if (updateEvents === true) {
          alert('Event is deleted successfully!');
        } else {
          alert('Something went wrong! Please try again!');
        }
        this.userService.getEventForUser(this.userService.getUserId()).subscribe((event) => {
        this.eventsList = event; // Event list of logged in user
        });
      });
    }

  }

  editEvent(event) {

         const navigationExtras: NavigationExtras = {
           queryParams: {
            'eventId' : event.eventId,
            'eventName': event.eventName,
            'eventDetails': event.eventDetails,
            'eventContact': event.eventContact,
            'eventVenue': event.eventVenue,
            'eventDate': event.eventDate
            }
           };
           this.router.navigate(['edit'], navigationExtras );
         }

         cancelEvent(event) {
          if (confirm('Do you want to Cancel this Ticket?')) {
            this.eventObj.eventId = Number(event.eventId);
            this.eventObj.userId = Number(this.userService.getUserId());
            this.userService.cancelEvent(this.eventObj).subscribe(updateEvents => {
              if (updateEvents === true) {
                alert('Ticket is Canceled successfully!');
              } else {
                alert('Something went wrong! Please try again!');
              }
              this.userService.getBookedEvents(Number(this.userService.getUserId())).subscribe((eventB) => {
                if (eventB === null) {
                  this.show = true;
                } else {
                  this.bookedEventsList = eventB;
                }
              });
            });
          }
        }

}
