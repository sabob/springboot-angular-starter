import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './route/app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './view/login/login.component';
import {HomeComponent} from './view/home/home.component';
import {SampleComponent} from './view/sample/sample.component';
import {SecureComponent} from './view/secure/secure.component';
import {NotFoundComponent} from '@app/view/not-found/not-found.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InitService} from "@app/service/init.service";
import { NavbarComponent } from './component/menu/navbar.component';

@NgModule({
  entryComponents: [

  ],
  declarations: [
    NotFoundComponent,
    SampleComponent,
    SecureComponent,
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  providers: [
    NotFoundComponent,
    InitService,

    {
      provide: APP_INITIALIZER,
      useFactory: (service: InitService) => () => {
        return service.init();
      },
      deps: [InitService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
