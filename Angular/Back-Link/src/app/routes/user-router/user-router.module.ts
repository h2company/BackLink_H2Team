// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { RouterModule, Routes } from '@angular/router';

// import { DashboardComponent } from 'src/app/component/dashboard/dashboard.component';
// import { NeedAuthGuard } from '../need-auth.guard';
// import { UserManagerComponent } from 'src/app/component/dashboard/user-manager/user-manager.component';
// import { IndexComponent } from 'src/app/component/dashboard/index/index.component';

// const route : Routes = [
//     { path: 'dashboard', 
//     component: DashboardComponent, 
//     canActivate: [NeedAuthGuard],
//       children: [
//         { path: '', component: IndexComponent },
//         { path: 'user', component: UserManagerComponent }
//       ]
//   }
// ];

// @NgModule({
//   declarations: [],
//   imports: [RouterModule.forChild(route)],
//   exports: [ RouterModule ]
// })
export class UserRouterModule { }
