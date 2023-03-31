import { Component, OnInit } from '@angular/core';
import { HighScore } from '../high-score';
import { AdminService } from '../admin.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-listhighscoresbygrouptype',
  templateUrl: './listhighscoresbygrouptype.component.html',
  styleUrls: ['./listhighscoresbygrouptype.component.css']
})
export class ListhighscoresbygrouptypeComponent {
  
  //Aaron's code
unauth:boolean = false;

  highScores: HighScore[]

constructor( private router: Router, private admin: AdminService, private title: Title){}

ngOnInit(): void {
this.title.setTitle("High Scores By Group Type")

  this.admin.getHighScores().subscribe(data =>{
    this.highScores = data;
  }, error =>{
    console.log(error.status);
      if(error.status == '401'){
        this.unauth = true;
      } else {
        this.router.navigateByUrl("/")
      }
  })
}
}
