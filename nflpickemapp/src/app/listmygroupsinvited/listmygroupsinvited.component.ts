import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Groupuser } from '../groupuser';
import { PickemgroupService } from '../pickemgroup.service';

@Component({
  selector: 'app-listmygroupsinvited',
  templateUrl: './listmygroupsinvited.component.html',
  styleUrls: ['./listmygroupsinvited.component.css']
})
export class ListmygroupsinvitedComponent {

  groupusers: Groupuser[]

  constructor(private router: Router, private pickemgroupservice: PickemgroupService, private titleService: Title) { }

  unauth: boolean = false;

  ngOnInit(): void {
    this.titleService.setTitle("All Groups Invited");

    this.pickemgroupservice.getAllGroupsInvited().subscribe(
      data => {
        this.groupusers = data;
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
