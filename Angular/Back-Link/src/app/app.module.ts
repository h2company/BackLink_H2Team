import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRouterModule, RoutesComponent } from './routes/app-router/app-router.module';

import { HttpService } from './service/http.service';
import { AuthenticationService } from './service/authentication.service';
import { UserService } from './service/user.service';

import { AppComponent } from './app.component';
import { NeedAuthGuard } from './routes/need-auth.guard';


@NgModule({
  declarations: [
    AppComponent,
    RoutesComponent
  ],
  imports: [    
    CommonModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRouterModule
  ],
  providers: [
    HttpService,
    AuthenticationService,
    UserService,
    NeedAuthGuard
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
