import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from './group';
import { Groupuser } from './groupuser';

@Injectable({
  providedIn: 'root'
})
export class PickemgroupService {

  private groupsURL = "http://localhost:8080/nflpickem/groups";
  ///nflpickem/user/
  constructor(private http: HttpClient) { }


  public createGroup(group: Group): Observable<any> {
    return this.http.post<string>(this.groupsURL + "/create", group);
  }

  public joinGroupForUser(group: string, username: string): Observable<any> {
    return this.http.get<Group>(this.groupsURL + "/" + username + "/join/" + group);

  }

  public joinGroup(group: string): Observable<any> {
    return this.http.get<Group>(this.groupsURL + "/join/" + group);
  }

  public declineGroupInvitation(group: string): Observable<any> {
    return this.http.delete<Group>(this.groupsURL + "/decline/" + group);
  }

  public declineGroupInvitationForUser(group: string, username: string): Observable<any> {
    return this.http.delete<Group>(this.groupsURL + "/" + username + "/decline/" + group);
  }

  public inviteUserToGroup(group: string, username: string): Observable<any> {
    return this.http.get<Groupuser>(this.groupsURL + "/" + username + "/isinvitedto/" + group);
  }

  public getAllGroups(): Observable<any> {
    return this.http.get<Group[]>(this.groupsURL + "/allgroups");
  }

  public getLeaderBoardForGroup(group: string): Observable<any> {
    return this.http.get<Groupuser[]>(this.groupsURL + "/leaderboard/" + group);
  }

  public editGroup(group: Group): Observable<any> {
    return this.http.put<Group>(this.groupsURL + "/editgroup", group);
  }

  public getAllGroupsForUser(user: string): Observable<any> {
    return this.http.get<Groupuser[]>(this.groupsURL + "/allgroups/" + user);
  }

  public getAllGroupsInvited(): Observable<any> {
    return this.http.get<Groupuser[]>(this.groupsURL + "/byinvite");
  }

  public deleteGroup(group: string): Observable<any> {
    return this.http.get<String>(this.groupsURL + "/" + group);
  }







}
