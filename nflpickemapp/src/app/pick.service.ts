import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pick } from './pick';
@Injectable({
  providedIn: 'root'
})
export class PickService {

  
  private picksURL = "http://localhost:8080/nflpickem/picks";
  ///nflpickem/user/
  constructor(private http: HttpClient) { }


  public getNumberOfMissingPicks(username: string, group: string, week: number): Observable<any> {  
    return this.http.get<number>(this.picksURL + "/missing/ " + username + "/group/" + group + "/week/" + week);
  }

  public getByUsernameNGroupNWeek(username: string, group: string, week: number): Observable<any> {  
    return this.http.get<Pick[]>(this.picksURL + "/ " + username + "/group/" + group + "/week/" + week);
  }

  public makePicks(picks: Pick[]): Observable<any> {  
    return this.http.put<Pick[]>(this.picksURL + "/makepicks",picks);
  }



}
