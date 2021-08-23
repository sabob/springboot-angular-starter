import {Injectable} from "@angular/core";
import {environment} from '@app/./../environments/environment';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from "@angular/router";
import {Sample} from "@app/model/Sample";

@Injectable({providedIn: 'root'})
export class SampleService {

  banksUrl = environment.sampleUrl;

  constructor(private http: HttpClient, private router: Router) {
  }

 async getSample( query: string, page: number, pageSize: number ):Promise<Sample[]> {

   let result:any;

    try {

      let httpParams =new HttpParams();
      httpParams = httpParams.set("query", query);
      httpParams = httpParams.set("page", "" + page);
      httpParams = httpParams.set("pageSize", "" + pageSize);

      let httpOptions: Object = {

        headers: new HttpHeaders({}),
        responseType: 'json',
        params: httpParams
      };

      result = await this.http.get<any>(this.banksUrl, httpOptions).toPromise();

    } catch (err) {
      console.log("Error: ", err);
    }
      return result;
  }
}
