import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Groupuser } from './groupuser';
import { HighScore } from './high-score';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  
  private adminURL = "http://localhost:8080/nflpickem/admin";
  ///nflpickem/user/
  constructor(private http: HttpClient) { }

  public getNumberOfPicksForWeek(week: number): Observable<any> {  
    return this.http.get<number>(this.adminURL + "/numpicks/byweek/" + week);
  }

  public getTotalNumberOfUsers(): Observable<any> {  
    return this.http.get<number>(this.adminURL + "/numusers");
  }

  public getTotalNumberOfPicks(): Observable<any> {  
    return this.http.get<number>(this.adminURL + "/numpicks");
  }

  public getTotalNumberOfGroups(): Observable<any> {  
    return this.http.get<number>(this.adminURL + "/numgroups");
  }

  public getAllStandings(): Observable<any> {  
    return this.http.get<Groupuser>(this.adminURL + "/allstandings");
  }

  public getHighScores(): Observable<any> {  
    return this.http.get<HighScore[]>(this.adminURL + "/highscores");
  }

}
