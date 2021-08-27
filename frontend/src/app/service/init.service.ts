import {Injectable} from "@angular/core";
import appUtils from "@app/utils/appUtils";

@Injectable({providedIn: 'root'})
export class InitService {

  constructor() {
  }

  async init() {

    try {
      appUtils.setupAndStoreAppToken();

    } catch (err) {
      console.error("Error: ", err);
    }
  }

}
