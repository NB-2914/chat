import { Injectable } from '@angular/core';
import { HttpSendService } from './http-send.service';
import { GlobalConstants } from './common/global-constants';
import { SendPassword } from './send-password.interface';
import { map, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class GetDisplayService {

  constructor(private httpSendService: HttpSendService){}
  anmeldeDaten:SendPassword = {id: sessionStorage["author"], password:"q"}
 getDisplay():Observable <any> {
    return this.httpSendService.getDisplay(this.anmeldeDaten).pipe(
      map(response => {
        GlobalConstants.display = response.displayName
        return response
      })
    )
 }

}
