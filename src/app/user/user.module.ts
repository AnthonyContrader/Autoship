import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserRoutingModule } from './user-routing.module';
import { GeneralInfoComponent } from './general-info/general-info.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { AcquistaComponent } from './acquista/acquista.component';



@NgModule({
  declarations: [GeneralInfoComponent,EditProfileComponent, AcquistaComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ]
})
export class UserModule { }

