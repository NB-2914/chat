import { Injectable } from '@angular/core';
import { Eingabe } from './eingabe.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SendPassword } from './send-password.interface';
 
import { GlobalConstants } from './common/global-constants';

@Injectable({
  providedIn: 'root'
})
export class HttpSendService {
  
  constructor(private http: HttpClient) { }

  sendData(comment: Eingabe): Observable<Eingabe>{
    return this.http.post<Eingabe>(GlobalConstants.ipAdress +":8080/chat/add", comment)
  }
  sendPassword(login:SendPassword): Observable<any>{
    return this.http.post<SendPassword>(GlobalConstants.ipAdress + ":8080/auth/login",login);//path ändern
  }
  sendRegistration(registration:SendPassword): Observable<any>{
    return this.http.post<any>(GlobalConstants.ipAdress + ":8080/auth/register", registration) // path ändern
  }
  getDisplay(id: SendPassword ): Observable<any>{

    return this.http.post<any>(GlobalConstants.ipAdress + ":8080/auth/name", id)
    
  }
}

//