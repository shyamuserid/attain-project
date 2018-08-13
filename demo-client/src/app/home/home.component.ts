import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting;

  constructor(private app: AppService, private http: HttpClient) {
    http.get('users/greeting', {responseType: 'text', headers: app.globalHeaders}).subscribe(data => this.greeting = data);
  }

  authenticated() {
    console.log(`Authenticated status on HomeComponent load: ${this.app.authenticated}`);
    return this.app.authenticated;
  }
}
