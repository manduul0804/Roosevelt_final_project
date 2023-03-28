import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ListallusersComponent } from './listallusers/listallusers.component';
import { ListgamesbyweekComponent } from './listgamesbyweek/listgamesbyweek.component';
import { ListgroupsComponent } from './listgroups/listgroups.component';
import { ListhighscoresbygrouptypeComponent } from './listhighscoresbygrouptype/listhighscoresbygrouptype.component';
import { ListmygroupleadersComponent } from './listmygroupleaders/listmygroupleaders.component';
import { ListmygroupsComponent } from './listmygroups/listmygroups.component';
import { ListmygroupsinvitedComponent } from './listmygroupsinvited/listmygroupsinvited.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [ 
{
  path: '',
  redirectTo: '/allusers',
  pathMatch: 'full'

},
{
  path: 'login',
  component: LoginComponent

},
{
  path: 'register',
  component: RegisterComponent,
  

},
{
  path: 'allusers',
  component: ListallusersComponent, 
  canActivate: [AuthGuard]

},
{
  path: 'mygroups',
  component: ListmygroupsComponent, 
  canActivate: [AuthGuard]

},
{
  path: 'highscoresbygrouptype',
  component: ListhighscoresbygrouptypeComponent, 
  canActivate: [AuthGuard]

},
{
  path: 'gamesbyweek',
  component: ListgamesbyweekComponent, 
  canActivate: [AuthGuard]

},



{
  path: 'allgroups',
  component: ListgroupsComponent, 
  canActivate: [AuthGuard]

},


{
  path: 'mygroupleaders',
  component: ListmygroupleadersComponent, 
  canActivate: [AuthGuard]

},


{
  path: 'myinvited',
  component: ListmygroupsinvitedComponent, 
  canActivate: [AuthGuard]

},
{
  path: '**',
  redirectTo: '/allusers',
  pathMatch: 'full'
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
