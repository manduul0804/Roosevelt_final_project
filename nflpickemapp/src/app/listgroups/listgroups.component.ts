import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Group } from '../group';
import { PickemgroupService } from '../pickemgroup.service';

@Component({
  selector: 'app-listgroups',
  templateUrl: './listgroups.component.html',
  styleUrls: ['./listgroups.component.css']
})
export class ListgroupsComponent {

  groups: Group[];


  constructor(private router: Router, private pickemgroupservice: PickemgroupService, private titleService: Title) { }


  unauth: boolean = false;

  ngOnInit(): void {
    this.titleService.setTitle("All Groups");

    this.pickemgroupservice.getAllGroups().subscribe(
        data => {
          this.groups = data;
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
