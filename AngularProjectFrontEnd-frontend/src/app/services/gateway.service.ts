import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GatewayService {
  constructor(private http: HttpClient) { }
  
  Login(username: string, password: string): Observable<any> {
    const apiUrl = "http://localhost:8099/user-service/api/users/login";
    console.log('user service');



    let token = this.http.post(apiUrl, {
      username,
      password
    }, {
      headers: new HttpHeaders({'Content-Type': 'application/json'}),
      responseType: 'text' as 'json'
    });

    console.log("the token is :",token);



    return token;
  }

  url: string = "http://localhost:9999"

  getAuthHeader(): HttpHeaders{
    let token = localStorage.getItem("authToken") 
    let headers = new HttpHeaders();
    if (token) {
      // token = token.replace(/"/g, '');
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers
  }

  getUser(username: string): Observable<any>{
    return this.http.get(`${this.url}/service_provider?role=USER&username=${username}`,{headers : this.getAuthHeader()}) 
  }

  authenticate(username:string,password:string): Observable<any>{
    let cred = {
      "username" : username,
      "password" : password
    }
    return this.http.post(`${this.url}/auth/login`,cred)
  }

  
}

