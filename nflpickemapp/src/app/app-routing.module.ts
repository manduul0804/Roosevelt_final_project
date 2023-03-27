import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ListallusersComponent } from './listallusers/listallusers.component';
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
