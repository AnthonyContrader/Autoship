import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CorriereLayoutComponent } from '../layout/corriere-layout/corriere-layout.component';
import { CorriereDashboardComponent } from './corriere-dashboard/corriere-dashboard.component';
import { GeneralInfoComponent } from './general-info/general-info.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { SpedizioneComponent } from './spedizione/spedizione.component';

const routes: Routes = [
    { path: 'corriere-dashboard', component: CorriereLayoutComponent, children:[
    { path: '', component: CorriereDashboardComponent},
    { path: 'general-info', component: GeneralInfoComponent},
    { path: 'edit-profile', component: EditProfileComponent},
    { path: 'spedizione', component: SpedizioneComponent}]
    }];
    @NgModule({
        imports: [RouterModule.forChild(routes)],
        exports: [RouterModule]
      })
      export class CorriereRoutingModule { }