import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

// @ts-ignore
import store from '@app/store/store';

// @ts-ignore
import appUtils from '@app/utils/appUtils';
import {AuthService} from "@app/service/auth.service";

@Injectable()
@Component({
  templateUrl: 'logout.component.html',
  styleUrls: ['logout.component.css']
})
export class LogoutComponent implements OnInit {

  next: string;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    console.error("LOGOUT")
    this.logout();
  }

  async logout() {
    await this.authService.logout();
    this.router.navigateByUrl('/home');
  }
}
