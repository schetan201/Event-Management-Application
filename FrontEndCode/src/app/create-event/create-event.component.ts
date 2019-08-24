import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { CreateEventService } from './create-event.service';
import { UserService } from '../shared/user.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {

  // Regular expression for validationg phone number
  phoneRegex = '^((\\+91-?)|0)?[0-9]{10}$';

  formVar: FormGroup;
  objectEvent: any;
  submitted = false;
  userId: number;
  min_Date: Date;

  constructor(private fb: FormBuilder, private createService: CreateEventService,
     private userService: UserService,
     private router: Router) {}

  ngOnInit() {
this.min_Date = new Date();
    // Validations : All fields are required and phone number format
    this.formVar = this.fb.group({
      eventName: ['', Validators.required],
      eventDetails: ['', Validators.required],
      eventContact: ['', [Validators.required, Validators.pattern(this.phoneRegex)]],
      eventVenue: ['', Validators.required],
      eventDate: ['', Validators.required]/*,
      userId: [Number(this.userService.getUserId())]*/
  });

  }

  // Function to be used on the html for catching errors
  get f() { return this.formVar.controls; }

onSubmit() {
  this.submitted = true;
  // stop here if form is invalid
  if (this.formVar.invalid) {
      return;
  }
this.userId = Number(this.userService.getUserId());
this.formVar.value.userId = this.userId;
  this.createService.addEvents(JSON.stringify(this.formVar.value))
  .subscribe(
    data => {
      if (data === true) {
        alert('Event Added successfully!');
        if (confirm('Do you want to add one more event?')) {
          this.formVar.reset();
        } else {
          this.router.navigate(['/userProfile']);
        }
    } else {
      alert('Something went Wrong! Please try again');
      this.formVar.reset();
    }
  },
    error => {
      alert('Something went Wrong! Please try again');
      this.formVar.reset();
    });
}
}
