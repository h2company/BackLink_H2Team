import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from 'src/app/component/login/login.component';
import { DashboardComponent } from 'src/app/component/dashboard/dashboard.component';
import { NotFoundComponent } from 'src/app/component/not-found/not-found.component';
import { RecoverComponent } from 'src/app/component/recover/recover.component';
import { SignupComponent } from 'src/app/component/signup/signup.component';

import { UserManagerComponent } from 'src/app/component/dashboard/user-manager/user-manager.component';
import { HeaderComponent } from 'src/app/component/blocks/header/header.component';
import { SidebarComponent } from 'src/app/component/blocks/sidebar/sidebar.component';
import { FooterComponent } from 'src/app/component/blocks/footer/footer.component';
import { NeedAuthGuard } from '../need-auth.guard';
import { NotAuthGuard } from '../not-auth.guard';
import { IndexComponent } from 'src/app/component/dashboard/index/index.component';
import { AddBacklinkComponent } from 'src/app/component/dashboard/add-backlink/add-backlink.component';
import { AddActionComponent } from 'src/app/component/dashboard/add-action/add-action.component';
import { StatisticalAccessComponent } from 'src/app/component/dashboard/statistical-access/statistical-access.component';
import { PointMemberComponent } from 'src/app/component/dashboard/point-member/point-member.component';
import { PointLogComponent } from 'src/app/component/dashboard/point-log/point-log.component';
import { EditUserComponent } from 'src/app/component/dashboard/user-manager/edit-user/edit-user.component';
import { ListUserComponent } from 'src/app/component/dashboard/user-manager/list-user/list-user.component';
import { AddUserComponent } from 'src/app/component/dashboard/user-manager/add-user/add-user.component';
import { LogoutComponent } from 'src/app/component/dashboard/logout/logout.component';
import { AppRouter } from '../app-router.model';

const routes: Routes = [
  { path: '', redirectTo: `/${AppRouter.INDEX}`, pathMatch: 'full' },
  {
    path: AppRouter.INDEX,
    component: DashboardComponent,
    canActivate: [NeedAuthGuard],
    children: [
      { path: '', component: IndexComponent },
      {
        path: 'users',
        component: UserManagerComponent,
        children: [
          { path: '', component: ListUserComponent },
          { path: 'add', component: AddUserComponent },
          { path: ':id', component: EditUserComponent },
        ]
      },
      { path: 'logout', component: LogoutComponent }
      { path: AppRouter.ADD_BACKLINK, component: AddBacklinkComponent },
      { path: AppRouter.ADD_ACTION, component: AddActionComponent },
      { path: AppRouter.STATISTICAL_ACCESS, component: StatisticalAccessComponent },
      { path: AppRouter.POINT_MEMBER, component: PointMemberComponent },
      { path: `${AppRouter.POINT_MEMBER}/:id`, component: PointMemberComponent },
      { path: AppRouter.POINT_LOG, component: PointLogComponent }
    ]
  },
  {
    path: AppRouter.SIGNIN,
    component: LoginComponent,
    canActivate: [NotAuthGuard],
  },
  { 
    path: AppRouter.RECOVER, 
    component: RecoverComponent,
    canActivate: [NotAuthGuard],
  },
  { 
    path: AppRouter.SIGNUP, 
    component: SignupComponent,
    canActivate: [NotAuthGuard]
  },
  { path: AppRouter.NOT_FOUND, component: NotFoundComponent },
  { path: '**', redirectTo: `/${AppRouter.NOT_FOUND}`, pathMatch: 'full' }
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
  UserManagerComponent,
  NotFoundComponent,
  HeaderComponent,
  SidebarComponent,
  FooterComponent,
  IndexComponent,
  AddActionComponent,
  AddBacklinkComponent,
  StatisticalAccessComponent,
  PointMemberComponent,
  PointLogComponent,
  ListUserComponent,
  AddUserComponent
];
