import {Sample} from "@app/model/Sample";

class Store {

  private static _instance = new Store();

  private _samples: Sample[]
  appToken: any = null

  private constructor() {
  }


  get samples(): Sample[] {
    return this._samples;
  }

  set samples(value: Sample[]) {
    this._samples = value;
  }

  hasSamples() {
    if (this.samples == null || this._samples.length == 0) {
      return false;

    } else {
      return true;
    }
  }

  getAppToken(): any {
    return this.appToken;
  }

  setAppToken(appToken: any) {
    this.appToken = appToken;
  }

  reset() {
  }

static get instance() {
  return this._instance;
}
}


export const store = Store.instance;
export default store;

