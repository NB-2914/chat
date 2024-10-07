import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SendPassword } from '../send-password.interface';
import { HttpSendService } from '../http-send.service';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrieren',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registrieren.component.html',
  styleUrl: './registrieren.component.scss'
})
export class RegistrierenComponent {
  username : String = "";
  password : String = "";

  registration: SendPassword = {id: this.username, password: this.password}

  constructor(private HttpSendService:HttpSendService, private router:Router){}



  dataSend(){
    this.registration.id = this.username;
    this.registration.password = this.password;
    return this.HttpSendService.sendRegistration(this.registration).subscribe({
      next: response => {
        if (response.status == 200){
          sessionStorage["author"] = this.registration.id
          sessionStorage["isLoggedIn"] = true
          return this.router.navigate(["/chat"])
        }else{
          return this.router.navigate(["/error"])
        }
      },
      error: err =>{
        return this.router.navigate(["/error"])
      }}
    )
    
  }
} 
