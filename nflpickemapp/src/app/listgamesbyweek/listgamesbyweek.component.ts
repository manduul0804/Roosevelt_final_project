import { Component } from '@angular/core';
import { Game } from '../game';
import { Router } from '@angular/router';
import { GameService } from '../game.service';
import { Title } from '@angular/platform-browser';



@Component({
  selector: 'app-listgamesbyweek',
  templateUrl: './listgamesbyweek.component.html',
  styleUrls: ['./listgamesbyweek.component.css']
})
export class ListgamesbyweekComponent {

  games: Game[];

  constructor(private router: Router, private gameservice: GameService, private titleService: Title) { }

  unauth: boolean = false;

  ngOnInit(): void {
    this.titleService.setTitle("All games for week 1");

    this.gameservice.getAllByWeek(1).subscribe(
        data => {
          this.games = data;
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
