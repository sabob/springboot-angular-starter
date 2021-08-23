import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import appUtils from '@app/utils/appUtils';

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
      return this.handleAccess(state);
    }
  }

  handleAccess(state: RouterStateSnapshot) {

    console.error("you are not authenticated");
    const urlTree = this.router.parseUrl('login');

    if (state.url) {
      urlTree.queryParams['error'] = 'not-authenticated';
      urlTree.queryParams['next'] = state.url;
    }
    return urlTree;
  }
}

