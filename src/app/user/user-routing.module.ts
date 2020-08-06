import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLayoutComponent } from '../layout/user-layout/user-layout.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { GeneralInfoComponent } from './general-info/general-info.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { AcquistaComponent } from './acquista/acquista.component';
const routes: Routes = [
    { path: 'user-dashboard', component: UserLayoutComponent, children:[
      { path: '', component: UserDashboardComponent},
      { path: 'general-info', component: GeneralInfoComponent},
      { path: 'edit-profile', component: EditProfileComponent},
      { path: 'acquista', component: AcquistaComponent}
    ]}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class UserRoutingModule { }