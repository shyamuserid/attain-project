import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

@Injectable()
export class AppService {

    authenticated = false;

    constructor(private http: HttpClient) {
    }

    authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get('user', { headers: headers })
            .pipe(
                catchError(this.handleError('authenticate', null))
            )
            .subscribe(response => {
                if (response && response.status === 200) {
                    this.authenticated = true;
                } else {
                    this.authenticated = false;
                }
                return callback && callback();
            });
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            this.authenticated = false;

            if (error.status === 400) {
                console.error('Encountered HTTP 400 trying to authenticate');
            } else if (error.status === 401) {
                console.log('Failed to authenticate with HTTP 401');
            } else {
                console.error(error);
                alert(`${operation} failed - check the console for more information`);
            }
            return of(result as T);
        };
    }
}
