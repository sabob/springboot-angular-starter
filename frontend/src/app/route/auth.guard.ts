import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from '@angular/router';
import appUtils from '@app/utils/appUtils';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (appUtils.isAuthenticated()) {
      console.log("you are authenticated");
      return true;

    } else {
      console.error("you are not authenticated");
      const urlTree = this.router.parseUrl('login');
      urlTree.queryParams['error'] = 'not-authenticated';
      urlTree.queryParams['next'] = state.url;
      return urlTree;
    }
  }
}
