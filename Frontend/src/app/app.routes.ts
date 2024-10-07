import { Routes } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { LoginComponent } from './login/login.component';
import { ChatComponent } from './chat/chat.component';


export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'error', component: ErrorComponent},
    {path: 'chat', component: ChatComponent}
];
