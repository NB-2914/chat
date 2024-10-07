import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SendPassword } from '../send-password.interface';
import { HttpSendService } from '../http-send.service';
import { FormsModule } from '@angular/forms';
import { IsLoggedInService } from '../is-logged-in.service';
import { Router } from '@angular/router';
import { GlobalConstants } from '../common/global-constants';

@Component({
  selector: 'app-anmelden',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './anmelden.component.html',
  styleUrl: './anmelden.component.scss'
})
export class AnmeldenComponent {
  username = ""
  password = ""

  anmeldeDaten: SendPassword = {id : this.username, password : this.password} 
  
  constructor(private httpSendService: HttpSendService, private isloggedIn:IsLoggedInService,private router:Router){};
  temp: any;


  dataSend(){
    this.anmeldeDaten.id = this.username;
    this.anmeldeDaten.password = this.password;
    this.httpSendService.sendPassword(this.anmeldeDaten).subscribe({
      next: response => {
        if(response.status == 200){
          sessionStorage["author"] = this.username;
          sessionStorage["isLoggedIn"] = true;
          return this.router.navigate(['/chat'])
        }else{
          return this.router.navigate(['/error'])
        }
      },
      error: err =>{
        return this.router.navigate(["/error"])
      }
      
  })
  }

}
