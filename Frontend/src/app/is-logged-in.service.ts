import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IsLoggedInService {
  public loggedIn = new BehaviorSubject<any>({
    loggedIn: false
  })
}
