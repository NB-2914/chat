import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Eingabe } from './eingabe.interface';
import { User } from './user.interfaces';

import { GlobalConstants } from './common/global-constants';

@Injectable({
  providedIn: 'root'
})
export class HttpService { 
  
  constructor(private http:HttpClient) { }
  
  getData():Observable<Eingabe[]>{
    return this.http.get<Eingabe[]>(GlobalConstants.ipAdress + ":8080/chat/list");
  }
  getLogin():Observable<User>{
    return this.http.get<User>(GlobalConstants.ipAdress + "paht")// path hinzufügen
  }
}
