import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Groupuser } from '../groupuser';
import { PickemgroupService } from '../pickemgroup.service';


@Component({
  selector: 'app-listmygroups',
  templateUrl: './listmygroups.component.html',
  styleUrls: ['./listmygroups.component.css']
})

export class ListmygroupsComponent {

  groupusers: Groupuser[];

  constructor(private router: Router, private pickemgroupservice: PickemgroupService, private titleService: Title) { }

  unauth: boolean = false;

  ngOnInit(): void {
    this.titleService.setTitle("My Groups");
    const user = sessionStorage.getItem('user');

    if (user !== null) {
      this.pickemgroupservice.getAllGroupsForUser(user).subscribe(
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
}