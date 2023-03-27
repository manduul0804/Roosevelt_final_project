import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = "http://localhost:8080/nflpickem/user";
  ///nflpickem/user/
  constructor(private http: HttpClient) { }

  public login(user: User): Observable<any> {  
    return this.http.post<User>(this.usersURL + "/login", user, {withCredentials: true});
  }

  public getAllUsers(): Observable<any> {  
    return this.http.get<User[]>(this.usersURL + "/allusers", {withCredentials: true});
  }

  public logout(): Observable<any> {
    sessionStorage.clear();
    return this.http.get<User>(this.usersURL + "/logout",{withCredentials: true});
  }

  public register(user: User): Observable<any> {  
    return this.http.post<User>(this.usersURL, user, {withCredentials: true});
  }

  public getUserByName(name: string): Observable<any> {
    return this.http.get<User>(this.usersURL + "/" + name, {withCredentials: true});
  }

  public delUserByName(name: string): Observable<any> {
    return this.http.delete<string>(this.usersURL + "/" + name, {withCredentials: true});
  }

  public updateUser(user: User): Observable<any> {  
    return this.http.put<string>(this.usersURL, user, {withCredentials: true});
  }

}
