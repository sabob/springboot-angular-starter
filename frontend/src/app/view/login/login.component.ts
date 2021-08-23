import {Component, Injectable, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Credentials} from '../../model/Credentials';
import {ActivatedRoute, Router} from '@angular/router';

// @ts-ignore
import store from '@app/store/store';

// @ts-ignore
import appUtils from '@app/utils/appUtils';
import {NgForm} from "@angular/forms";
import {AuthService} from "@app/service/auth.service";
import Constants from "@app/model/enums/Constants";

@Injectable()
@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild('loginForm') loginForm: NgForm;

  goto: string;

  authError: string;

  credentials = new Credentials();

  title = 'Login';

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

      this.authError = params[Constants.AUTH_ERROR] || null;

      // Next url to navigate to after login succeeds
      this.goto = params[Constants.GOTO_URL_PARAM] || null;
    });
  }

  async login(event: Event) {

    event.preventDefault();

    await this.authService.login(this.credentials);

    if (this.goto) {
      await this.router.navigateByUrl(this.goto);
    } else {
      await this.router.navigateByUrl('/');
    }
  }
}
