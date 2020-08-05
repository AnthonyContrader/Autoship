import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component'

/**
 * Questo è un modulo di routing. Essendo il modulo principale deve UNICAMENTE 
 * eseguire il redirect sul persorso /login (modulo di login) e NON DEVE contenere
 * altri percorsi: questi vanno specificati nei vari sotto-moduli di routing.
 * 
 * @see LoginRoutingModule
 * 
 * @author Vittorio Valent
 */
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo:'/oggetto', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
