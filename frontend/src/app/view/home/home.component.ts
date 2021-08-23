import {Component, Injectable, OnInit} from '@angular/core';
import appUtils from "@app/utils/appUtils";

@Injectable()
@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  authenticated = false;

  constructor() {
  }

  ngOnInit() {

    this.authenticated = appUtils.isAuthenticated();
  }

}
