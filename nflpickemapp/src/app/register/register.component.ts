import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  //we need a student model for the form
  user: User = new User();

  nameinuse: boolean = false;

  constructor(private router: Router, private userservice: UserService, private titleService: Title) { }

  
  ngOnInit(): void {
    this.titleService.setTitle("Login");
  }

  onSubmit() {
    this.userservice.register(this.user).subscribe(
      data => {
        this.router.navigateByUrl("/login");
      }, error => {
        this.user.username = "";
        this.nameinuse = true;
      }
    );

  }

}
