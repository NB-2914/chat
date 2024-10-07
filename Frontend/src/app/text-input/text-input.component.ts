
import { FormsModule } from '@angular/forms';
import { Component, Injectable } from '@angular/core';
import { HttpSendService } from '../http-send.service';
import { CommonModule } from '@angular/common';
import { Eingabe } from '../eingabe.interface';

@Injectable({providedIn:'root'})



@Component({
  selector: 'app-text-input',
  standalone: true,
  imports: [ FormsModule, CommonModule],
  templateUrl: './text-input.component.html',
  styleUrl: './text-input.component.scss'
})
export class TextInputComponent {
  newMessage: Eingabe = {author: sessionStorage.getItem('author')?.toString() as String, message:'', color:'100000'};

  constructor(private httpSendService: HttpSendService){}

  sendMessage():void{
    this.httpSendService.sendData(this.newMessage).subscribe(
      response => {
      }
    );
    window.location.reload()
  }
}
 