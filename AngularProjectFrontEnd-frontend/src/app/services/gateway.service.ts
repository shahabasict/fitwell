import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { text } from 'stream/consumers';
 
@Injectable({
  providedIn: 'root'
})
export class GatewayService {

  private body = ""
 
  constructor(private http:HttpClient) { }
  
  Login() : Observable<any> {
    let apiUrl = "http://localhost:8080/api/users/login"
    console.log('user service')
    
    return this.http.post(apiUrl , {
      "username": "John",
      "password": "123"
  }  ,  {
    headers: new HttpHeaders({'Content-Type' : 'application/json'}),
    responseType : 'text' as 'json'
  } 
 )
  }


 
}