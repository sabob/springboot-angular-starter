import {Component, Injectable, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Credentials} from '../../model/Credentials';
import {ActivatedRoute, Router} from '@angular/router';

// @ts-ignore
import store from '@app/store/store';

// @ts-ignore
import appUtils from '@app/utils/appUtils';
import {NgForm} from "@angular/forms";
import {AuthService} from "@app/service/auth.service";

@Injectable()
@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild('loginForm') loginForm: NgForm;

  next: string;

  credentials = new Credentials();

  title = 'Login';

  result = '';

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      // Next url to navigate to after login succeeds
      this.next = params['next'] || null;
    });
  }

  async login(event: Event) {

    event.preventDefault();

    this.result = await this.authService.login(this.credentials);

    if (this.next) {
      await this.router.navigateByUrl(this.next);
    } else {
      await this.router.navigateByUrl('/');
    }
  }
}
