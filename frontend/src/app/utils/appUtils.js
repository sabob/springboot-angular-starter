import Constants from "@app/model/enums/Constants";
import stringUtils from "./stringUtils";
import store from "@app/store/store";

let utils = {
  setupAndStoreAppToken() {

    try {
      let claimBase64Token = utils.getCookie(Constants.APP_TOKEN);

      if (stringUtils.isEmpty(claimBase64Token)) {
        return;
      }

      let token = atob(claimBase64Token);
      let appToken = JSON.parse(token);

      store.setAppToken(appToken);

      return appToken;

    } catch (err) {
      console.error(err);
    }
  },

  sleep(milliseconds) {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
  },

  getCookie(name) {
    name = name + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return null;
  },

  hasCookie(name) {
    return this.getCookie(name) != null;
  },

  isProdProfile() {
    let appToken = this.getAppToken();
    let profile = appToken.profile;
    return profile == 'prod';
  },

  isAdmin() {
    let appToken = this.getAppToken();
    if (appToken) {
      return appToken.admin;
    }
    return false;
  },

  getAppToken() {
    let appToken = store.getAppToken();
    return appToken;
  },

  isAuthenticated() {

    let appToken = this.getAppToken();

    if (appToken == null) {
      return false;
    }

    return true;

    let username = appToken.username;
    if (username && username.length > 0) {
      return true;
    }
    return false;
  },

  getUsername() {
    let token = store.getAppToken();

    if (token) {
    return token.username;
    } else {
      return "Anonymous";
    }
  },

  canAccessRoute(route) {
    // unsecure flag allows us to bypass authentication for certain routes eg. login
    if (route.meta.unsecure === true) {
      return true;
    }

    let appToken = this.getAppToken();

    if (appToken == null) {
      authService.setupAppTokens();

      appToken = this.getAppToken();

      if (appToken == null) {
        return false;
      }
    }

    return true;
  },

  deleteCookie(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
  }
}

export default utils;
