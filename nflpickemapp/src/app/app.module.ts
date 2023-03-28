import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CredentialsInterceptor } from './credentials.interceptor';
import { UserService } from './user.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ListallusersComponent } from './listallusers/listallusers.component';
import { FormsModule } from '@angular/forms';
import { ListgroupsComponent } from './listgroups/listgroups.component';
import { ListgamesbyweekComponent } from './listgamesbyweek/listgamesbyweek.component';
import { ListmygroupsComponent } from './listmygroups/listmygroups.component';
import { ListmygroupsinvitedComponent } from './listmygroupsinvited/listmygroupsinvited.component';
import { ListmygroupleadersComponent } from './listmygroupleaders/listmygroupleaders.component';
import { ListhighscoresbygrouptypeComponent } from './listhighscoresbygrouptype/listhighscoresbygrouptype.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ListallusersComponent,
    ListgroupsComponent,
    ListgamesbyweekComponent,
    ListmygroupsComponent,
    ListmygroupsinvitedComponent,
    ListmygroupleadersComponent,
    ListhighscoresbygrouptypeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, [ {provide: HTTP_INTERCEPTORS, useClass: CredentialsInterceptor, multi: true}]],
  bootstrap: [AppComponent]
})
export class AppModule { }
