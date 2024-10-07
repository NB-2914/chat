import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Eingabe } from '../eingabe.interface';
import {  CommonModule } from '@angular/common';
import { GlobalConstants } from '../common/global-constants';
import { GetDisplayService } from '../get-display.service';

@Component({
  selector: 'app-text-field',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './text-field.component.html',
  styleUrl: './text-field.component.scss'
})
export class TextFieldComponent implements OnInit {

  messages: Eingabe[] = [];
  public benutzer: string = "";
  constructor(private httpService:HttpService, private getDisplay:GetDisplayService){}

  ngOnInit(): void{
    this.getDisplay.getDisplay().subscribe({
      next: response =>{
        this.benutzer = GlobalConstants.display
        this.httpService.getData().subscribe({
          next: response => {
            this.messages  = response;
        }
      })
      }

    })
    

  }

}
