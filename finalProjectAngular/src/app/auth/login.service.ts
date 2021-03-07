import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LogInDto } from './dto/log-in.dto';
import { HttpClient } from '@angular/common/http';
import { Session } from './models/session.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  logIn(logInDto: LogInDto): Observable<Session> {
    return this.httpClient.post<Session>(environment.API_URL + 'login', logInDto)
  }
}
