import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CorriereDashboardComponent } from './corriere-dashboard/corriere-dashboard.component';
import { SpedizioneComponent } from './spedizione/spedizione.component';
import { GeneralInfoComponent } from './general-info/general-info.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { FormsModule } from '@angular/forms';
import { CorriereRoutingModule } from './corriere-routing.module';



@NgModule({
  declarations: [GeneralInfoComponent,EditProfileComponent,CorriereDashboardComponent, SpedizioneComponent],
  imports: [
    CommonModule,
    CorriereRoutingModule,
    FormsModule
  ]
})
export class CorriereModule { }
