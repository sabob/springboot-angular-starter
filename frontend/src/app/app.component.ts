import {Component, OnInit} from '@angular/core';
import {AuthService} from "@app/service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Sample App';

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
  }
}
