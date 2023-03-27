import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  //we need a student model for the form
  user: User = new User();

  upissue: boolean = false;

  constructor(private router: Router, private userservice: UserService, private titleService: Title) { }

  
  ngOnInit(): void {
    this.titleService.setTitle("Login");
  }

  onSubmit() {
    console.log(this.user);
    this.userservice.login(this.user).subscribe(
      data => {
        console.log(data);
        this.user = data;
        //string -> using sessionstorage
        sessionStorage.setItem('user', this.user.username);
        if (this.user.admin == true) {
          sessionStorage.setItem('admin', "true");
          
        }
        //localstorage
        // localStorage.setItem('user', this.user.username);
        // if (this.user.admin == true) {
        //   localStorage.setItem('admin', "true");
        // }





        //bug out, it worked..
        this.router.navigateByUrl("/studentlist");
      }, error => {
          this.upissue = true;
          //localStorage.clear();
          sessionStorage.clear();
      });
  }

}
