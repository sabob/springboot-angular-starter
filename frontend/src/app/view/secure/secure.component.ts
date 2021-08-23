import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import appUtils from "@app/utils/appUtils";

@Injectable()
@Component({
  templateUrl: 'secure.component.html',
  styleUrls: ['secure.component.css']
})
export class SecureComponent implements OnInit {

  username = '';

  constructor(private http: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.username = appUtils.getUsername();
  }
}
