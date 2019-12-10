import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule, ToastContainerModule } from 'ngx-toastr';
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
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { ModalModule } from 'ngx-bootstrap/modal';
import { LogoutComponent } from './component/dashboard/logout/logout.component';
import { NgxPrettyCheckboxModule } from 'ngx-pretty-checkbox';
import { TagInputModule } from 'ngx-chips';
import { AccountComponent } from './component/dashboard/account/account.component';
import { ActionService } from './service/action.service';
import { EditPointComponent } from './component/dashboard/point-member/edit-point/edit-point.component';
import { ListPointComponent } from './component/dashboard/point-member/list-point/list-point.component';

@NgModule({
  declarations: [
    AppComponent,
    RoutesComponent,
    ListUserComponent,
    EditUserComponent,
    LogoutComponent,
    AccountComponent,
    EditPointComponent,
    ListPointComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRouterModule,
    UiSwitchModule,
    BrowserAnimationsModule,
    ToastContainerModule,
    BsDatepickerModule,
    SweetAlert2Module.forRoot(),
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      progressBar: true
    }),
    BsDatepickerModule.forRoot(),
    ModalModule.forRoot(),
    NgxPrettyCheckboxModule,
    TagInputModule
  ],
  providers: [
    HttpService,
    AuthenticationService,
    UserService,
    NeedAuthGuard,
    ActionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
