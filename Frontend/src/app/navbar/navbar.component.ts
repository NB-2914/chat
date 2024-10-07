
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {

  constructor(private router:Router){}
  public isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.isLoggedIn = sessionStorage["isLoggedIn"];
  }
  logout(){
    sessionStorage.clear()
    this.router.navigate(['/login'])
  }
}
 