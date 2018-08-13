import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

@Injectable()
export class AppService {

  authenticated = false;
  globalHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.globalHeaders = headers;

        this.http.get('user', {headers: headers})
            .pipe(
                catchError(this.handleError('authenticate', null))
            )
            .subscribe(response => {
                this.authenticated = true;

            return callback && callback();
        });
    }

    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            this.authenticated = false;
            console.error(error);
            console.error(`Authenticated: ${this.authenticated}`);
            alert(`${operation} failed - check the console for more information`);
            return of(result as T);
        };
    }
}
