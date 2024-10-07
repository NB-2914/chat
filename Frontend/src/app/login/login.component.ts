import { Component } from '@angular/core';
import { AnmeldenComponent } from '../anmelden/anmelden.component';
import { RegistrierenComponent } from '../registrieren/registrieren.component';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [AnmeldenComponent, RegistrierenComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

}
