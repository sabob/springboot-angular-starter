import {Component, Injectable, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Injectable()
@Component({
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.css']
})
export class SampleComponent implements OnInit, OnDestroy {

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
  }

  ngOnDestroy() {
  }

}
