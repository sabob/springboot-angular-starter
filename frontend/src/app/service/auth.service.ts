import {Injectable, OnInit} from "@angular/core";
import {environment} from '@app/./../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import appUtils from "@app/utils/appUtils";
import {Credentials} from "@app/model/Credentials";
import Constants from "@app/model/enums/Constants";
import {store} from "@app/store/store";

@Injectable({providedIn: 'root'})
export class AuthService {

  loginUrl = environment.loginUrl;

  logoutUrl = environment.logoutUrl;

  constructor(private http: HttpClient, private router: Router) {
  }

  async login( credentials:Credentials ) {

    try {

      let httpOptions: Object = {

        headers: new HttpHeaders({}),
        responseType: 'json'
      };

      let result = await this.http.post<any>(this.loginUrl, credentials, httpOptions).toPromise();

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

    await this.router.navigateByUrl('/');
    location.reload();

  }


}
