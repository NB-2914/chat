import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { TextInputComponent } from './text-input/text-input.component';
import { TextFieldComponent } from './text-field/text-field.component';
import { ReloadComponent } from './reload/reload.component';
import { AnmeldenComponent } from './anmelden/anmelden.component';
import { CommonModule } from '@angular/common';
import { RegistrierenComponent } from './registrieren/registrieren.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ErrorComponent } from './error/error.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ErrorComponent,TextInputComponent,NavbarComponent ,TextFieldComponent, ReloadComponent, AnmeldenComponent, RegistrierenComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent implements OnInit{
  title = 'Chat';
  public loggedIn: boolean = sessionStorage["isLoggedIn"];

  constructor(private router:Router){}

  public error = false
  ngOnInit(): void {
    if (sessionStorage["isLoggedIn"]){
      this.router.navigate(['/chat'])
    }else{
      this.router.navigate(['/login'])
    }
  }
}
