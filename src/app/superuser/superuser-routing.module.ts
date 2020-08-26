import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GeneralInfoComponent } from './general-info/general-info.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { AcquistaComponent } from './acquista/acquista.component';
import { CarrelloComponent } from './carrello/carrello.component';
import { GestisciCarrelloComponent } from './carrello/gestisci-carrello/gestisci-carrello.component';
import { VisualizzaCarrelloComponent } from './carrello/visualizza-carrello/visualizza-carrello.component';
import { SuperuserLayoutComponent } from '../layout/superuser-layout/superuser-layout.component';
import { SuperuserDashboardComponent } from './superuser-dashboard/superuser-dashboard.component';
import { UsersComponent } from './users/users.component';
import { OggettoComponent } from './oggetto/oggetto.component';
import { MagazzinoComponent } from './magazzino/magazzino.component';
import { SpedizioneComponent } from './spedizione/spedizione.component';

const routes: Routes = [
    { path: 'superuser-dashboard', component: SuperuserLayoutComponent, children:[
      { path: '', component: SuperuserDashboardComponent},
      { path: 'users', component: UsersComponent},
      { path: 'edit-profile', component: EditProfileComponent},
      { path: 'general-info', component:  GeneralInfoComponent},
      { path: 'oggetto', component:  OggettoComponent},
      { path: 'magazzino', component:  MagazzinoComponent},
      { path: 'acquista', component: AcquistaComponent},
      { path: 'carrello', component: CarrelloComponent},
      { path: 'carrello/gestisci-carrello/:otp', component: GestisciCarrelloComponent},
      { path: 'carrello/visualizza-carrello/:otp', component: VisualizzaCarrelloComponent},
      { path: 'spedizione', component: SpedizioneComponent}]
    }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class SuperuserRoutingModule { }