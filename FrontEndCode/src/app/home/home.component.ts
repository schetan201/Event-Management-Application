import { Component, OnInit, Input } from '@angular/core';
import { HomeService } from './home.service';
import { Router, NavigationExtras } from '@angular/router';
import { UserService } from '../shared/user.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {



eventsList: {};
  constructor(private homeService: HomeService,
    private router: Router, private userService: UserService) { }
eventObj;
id;
  ngOnInit() {
    console.log('ngoninit');
    this.homeService.getEvents().subscribe((events) => {
      this.eventsList = events.json();
      console.log(this.eventsList);
    });
  }


  openConfirmationDialog(event) {
    if (event.userId === Number(this.userService.getUserId())) {
      alert('Sorry! This is your own Event. Can\'t book a ticket!');
      return;
    }
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'eventId' : event.eventId,
        'eventName': event.eventName,
        'eventDetails': event.eventDetails,
        'eventContact': event.eventContact,
        'eventVenue': event.eventVenue,
        'eventDate': event.eventDate,
        'userName': localStorage.getItem('username')
      }
    };
    this.router.navigate(['event'], navigationExtras);
  }
  }


