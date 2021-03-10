import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StorageService } from '../auth/storage.service';
import { Activity } from '../models/activity';
import { Client } from '../models/client';
import { Worker } from '../models/worker';

@Injectable({
  providedIn: 'root'
})


export class ActivityService {

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService
  ) { }

  getAllActivities(): Observable<Activity[]> {
    return this.httpClient.get<Activity[]>(environment.API_URL + 'activities', {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getActivity(id: number): Observable<Activity> {
    return this.httpClient.get<Activity>(environment.API_URL + 'activity/'+ id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getActivitiesByCenter(id: number): Observable<Activity[]> {
    return this.httpClient.get<Activity[]>(environment.API_URL + 'activities/center/'+ id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getWorkersByActivity(id: number): Observable<Worker[]> {
    return this.httpClient.get<Worker[]>(environment.API_URL + 'activity/'+ id + '/workers', {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getClientsByActivity(id: number): Observable<Client[]> {
    return this.httpClient.get<Client[]>(environment.API_URL + 'activity/'+ id + '/clients', {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  addActivity(activity: Activity): Observable<Activity> {
    return this.httpClient.post<Activity>(environment.API_URL + 'activity', activity ,{headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  updateActivity(id: number, activity: Activity): Observable<Activity> {
    return this.httpClient.put<Activity>(environment.API_URL + 'activity/'+ id, activity, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  deleteActivity(id: number): Observable<void> {
    return this.httpClient.delete<void>(environment.API_URL + 'activity/'+ id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  addWorkerToActivity(activityId: number, workerId: number): Observable<void> {
    return this.httpClient.post<void>(environment.API_URL + 'activity/' + activityId + '/worker/' + workerId, null, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  addClientToActivity(activityId: number, clientId: number): Observable<void> {
    return this.httpClient.post<void>(environment.API_URL + 'activity/' + activityId + '/client/' + clientId, null, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
}
