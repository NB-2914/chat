import { Component } from '@angular/core';
import { ReloadComponent } from '../reload/reload.component';
import { TextInputComponent } from '../text-input/text-input.component';
import { TextFieldComponent } from '../text-field/text-field.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [ReloadComponent, TextInputComponent, TextFieldComponent,RouterOutlet],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss'
})
export class ChatComponent {

}
