import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LoginComponent } from 'src/app/component/login/login.component';
import { DashboardComponent } from 'src/app/component/dashboard/dashboard.component';
import { NotFoundComponent } from 'src/app/component/not-found/not-found.component';
import { RecoverComponent } from 'src/app/component/recover/recover.component';
import { SignupComponent } from 'src/app/component/signup/signup.component';

import { NeedAuthGuard } from '../need-auth.guard';

const routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [NeedAuthGuard] },
  { path: 'signin', component: LoginComponent },
  { path: 'recover', component: RecoverComponent },
  { path: 'signup', component: SignupComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRouterModule { }

export const RoutesComponent = [
  LoginComponent,
  RecoverComponent,
  SignupComponent,
  DashboardComponent,
  NotFoundComponent
];
