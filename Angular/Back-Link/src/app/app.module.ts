import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { UiSwitchModule } from 'ngx-ui-switch';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';

import { AppRouterModule, RoutesComponent } from './routes/app-router/app-router.module';

import { HttpService } from './service/http.service';
import { AuthenticationService } from './service/authentication.service';
import { UserService } from './service/user.service';

import { AppComponent } from './app.component';
import { NeedAuthGuard } from './routes/need-auth.guard';
import { ListUserComponent } from './component/dashboard/user-manager/list-user/list-user.component';
import { EditUserComponent } from './component/dashboard/user-manager/edit-user/edit-user.component';

@NgModule({
  declarations: [
    AppComponent,
    RoutesComponent,
    ListUserComponent,
    EditUserComponent
  ],
  imports: [    
    CommonModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRouterModule, 
    UiSwitchModule,    
    SweetAlert2Module.forRoot(),
    ReactiveFormsModule 
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
