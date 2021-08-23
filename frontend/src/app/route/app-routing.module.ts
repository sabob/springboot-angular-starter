import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '@app/view/login/login.component';
import {HomeComponent} from '@app/view/home/home.component';

import {NotFoundComponent} from '@app/view/not-found/not-found.component';
import {SampleComponent} from "@app/view/sample/sample.component";
import {AuthGuard} from "@app/route/auth.guard";
import {SecureComponent} from "@app/view/secure/secure.component";
import {LogoutComponent} from "@app/view/logout/logout.component";

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'login', component: LoginComponent,
  },
  {
    path: 'logout', component: LogoutComponent,
  },
  {
    path: 'secure', component: SecureComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'sample', component: SampleComponent
      }
    ]
  },
    {
    path: '404', component: NotFoundComponent
  },
  {
    path: '**', redirectTo: '/404'
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: false})
  ],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
