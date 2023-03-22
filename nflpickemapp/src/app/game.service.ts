import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Game } from './game';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private gameURL = "http://localhost:8080/nflpickem/games";
  ///nflpickem/user/
  constructor(private http: HttpClient) { }


  public createGame(game: Game): Observable<any> {  
    return this.http.post<Game>(this.gameURL, game);
  }

  public getAll(): Observable<any> {  
    return this.http.get<Game[]>(this.gameURL + "/allgames");
  }

  public getAllByWeek(week: number): Observable<any> {  
    return this.http.get<Game[]>(this.gameURL + "/byweek/" + week);
  }

  public deleteByGameID(gid: number): Observable<any> {  
    return this.http.delete<string>(this.gameURL + "/" + gid);
  }

  public getNextKickoff(): Observable<any> {  
    return this.http.get<EpochTimeStamp>(this.gameURL + "/nextkickoff");
  }

  public updateGame(game: Game): Observable<any> {  
    return this.http.put<Game[]>(this.gameURL + "/update", game);
  }

  public scoreGame(game: Game): Observable<any> {  
    return this.http.put<Game[]>(this.gameURL + "/updatescore", game);
  }

}
