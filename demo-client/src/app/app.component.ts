import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private app: AppService, private http: HttpClient, private router: Router) {
      this.app.authenticate(undefined, undefined);
    }
    logout() {
      this.http.get('logout', {responseType: 'text', headers: this.app.globalHeaders}).subscribe(() => {
          this.app.authenticated = false;
          this.app.globalHeaders = null;
          console.log(`Status after logout: ${this.app.authenticated}`);
          this.router.navigateByUrl('/login');
      });
    }

}