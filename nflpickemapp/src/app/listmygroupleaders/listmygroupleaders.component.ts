import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { PickemgroupService } from '../pickemgroup.service';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';


@Component({
  selector: 'app-listmygroupleaders',
  templateUrl: './listmygroupleaders.component.html',
  styleUrls: ['./listmygroupleaders.component.css']
})

export class ListmygroupleadersComponent {
  groupLeaderboard: any[];
  groupId: 123;
  unauth: boolean = false;

  constructor(private router: Router, private pickemgroupservice: PickemgroupService, private titleService: Title) { }

  ngOnInit(): void {
    this.titleService.setTitle("Group Leaderboard");
    this.groupId = 123;
    this.pickemgroupservice.getLeaderBoardForGroup(String(this.groupId)).subscribe(
      data => {
        this.groupLeaderboard = data;
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