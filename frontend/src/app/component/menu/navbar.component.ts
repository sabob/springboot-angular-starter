import {Component, OnInit} from '@angular/core';
import {store} from "@app/store/store";
import {AuthService} from "@app/service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username = store.getAppToken().username;
  version = store.getAppToken().version;
  profile = store.getAppToken().profile;

  constructor( private router: Router, private authService: AuthService ) {
  }

  ngOnInit(): void {
  }

  async logout( event: Event) {
    event.preventDefault();
    await this.authService.logout();
  }
}
