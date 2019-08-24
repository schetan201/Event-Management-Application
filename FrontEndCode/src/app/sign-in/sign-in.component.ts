import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { UserService } from '../shared/user.service';
@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  // tslint:disable-next-line:max-line-length
  emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private userService: UserService) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.pattern(this.emailRegex)]],
      password: ['', Validators.required]
  });
  if (this.userService.isLoggedIn()) {
  this.router.navigateByUrl('/home');
  }
}
// convenience getter for easy access to form fields
get f() { return this.loginForm.controls; }
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }

    this.loading = true;

    this.userService.login(this.loginForm.value)
        .subscribe(
            data => {
                if (data === null) {
                  alert('Something went wrong! Please try again!');
                  this.loading = false;
                } else {
                  alert('Signed In Successfully');
                  localStorage.setItem('userName', data['userName']);
                  this.userService.setUserId(data['userId']);
                  this.userService.setToken(data['userName']);
                  this.router.navigate(['/home']);
                }
            },
            error => {
              alert('Something went wrong! Please try again!');
                this.loading = false;
            });
    }

  }


