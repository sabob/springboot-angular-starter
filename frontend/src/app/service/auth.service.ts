import {Injectable, OnInit} from "@angular/core";
import {environment} from '@app/./../environments/environment';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import appUtils from "@app/utils/appUtils";
import {Credentials} from "@app/model/Credentials";
import Constants from "@app/model/enums/Constants.js";
import {store} from "@app/store/store";

@Injectable({providedIn: 'root'})
export class AuthService {

  loginUrl = environment.loginUrl;

  logoutUrl = environment.logoutUrl;

  constructor(private http: HttpClient, private router: Router) {
  }

  async login( credentials:Credentials ) {

    try {

      let headers = new HttpHeaders({
      });
      headers.set('Content-Type', 'application/x-www-form-urlencoded');

      let httpOptions: Object = {
        headers,
      };

      let body: any = new FormData();
      body.append("username", credentials.username);
      body.append("password", credentials.password);

      let result = await this.http.post<any>(this.loginUrl, body, httpOptions).toPromise();

      appUtils.setupAndStoreAppToken();

      return result;

    } catch (err) {
      console.error("Error: ", err);
    }
  }

  async logout() {

    let result = await this.http.post<any>(this.logoutUrl, null).toPromise();
    store.setAppToken(null);
    appUtils.deleteCookie(Constants.APP_TOKEN);

    // Reload app at root url to ensure store or other cahes are cleared
    document.location.href='';

  }


}
