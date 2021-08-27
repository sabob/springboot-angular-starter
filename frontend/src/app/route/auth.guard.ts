import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import appUtils from '@app/utils/appUtils';
import Constants from "@app/model/enums/Constants";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (appUtils.isAuthenticated()) {
      return true;

    } else {
      console.error("Unauthorized! cannot access " + state.url)
       this.handleUnauthorizedRequest(state);
      return false;
    }
  }

  handleUnauthorizedRequest(state: RouterStateSnapshot) {

    document.location.href='login.html';

    // const urlTree = this.router.parseUrl('login');
    //
    // if (state.url.length > 2) {
    //   urlTree.queryParams[Constants.AUTH_ERROR] = "Access denied";
    //   urlTree.queryParams[Constants.GOTO_URL_PARAM] = state.url;
    // }
    //return urlTree;
  }
}

