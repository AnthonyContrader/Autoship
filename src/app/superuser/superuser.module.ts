import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SuperuserRoutingModule } from './superuser-routing.module';
import { GeneralInfoComponent } from './general-info/general-info.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { AcquistaComponent } from './acquista/acquista.component';
import { CarrelloComponent } from './carrello/carrello.component';
import { GestisciCarrelloComponent } from './carrello/gestisci-carrello/gestisci-carrello.component';
import { VisualizzaCarrelloComponent } from './carrello/visualizza-carrello/visualizza-carrello.component';
import { SuperuserDashboardComponent } from './superuser-dashboard/superuser-dashboard.component';
import { UsersComponent } from './users/users.component';
import { OggettoComponent } from './oggetto/oggetto.component';
import { MagazzinoComponent } from './magazzino/magazzino.component';
import { SpedizioneComponent } from './spedizione/spedizione.component';
@NgModule({
  declarations: [SuperuserDashboardComponent,GeneralInfoComponent,EditProfileComponent,SpedizioneComponent, UsersComponent, OggettoComponent, MagazzinoComponent,AcquistaComponent, CarrelloComponent, GestisciCarrelloComponent, VisualizzaCarrelloComponent],
  imports: [ 
    CommonModule,
    SuperuserRoutingModule,
    FormsModule
  ]
})
export class SuperuserModule { }
