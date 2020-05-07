import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JsonPipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RegisteruserService {
  constructor(private http:HttpClient)
  {

  }
  registerUser(user:User):Observable<any>
  { 
    return this.http.post("http://localhost:8084/register",user,{responseType: 'text' as 'json'});
  }
}