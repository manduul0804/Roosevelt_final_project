import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-listallusers',
  templateUrl: './listallusers.component.html',
  styleUrls: ['./listallusers.component.css']
})
export class ListallusersComponent {

  users: User[];


  constructor(private router: Router, private userservice: UserService, private titleService: Title) { }


  unauth: boolean = false;

  ngOnInit(): void {
    this.titleService.setTitle("All Users");

    this.userservice.getAllUsers().subscribe(
        data => {
          this.users = data;
        }, error => {
          console.log(error.status);
          if (error.status == '401') {
            this.unauth = true;
          } else {
            this.router.navigateByUrl("/");
          }
         
          
        }
    );
   
    
    
  }

}
