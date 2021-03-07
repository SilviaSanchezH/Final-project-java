import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StorageService } from '../auth/storage.service';
import { Center } from '../models/center';
import { Client } from '../models/client';
import { User } from '../models/user';
import { Worker } from '../models/worker';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService
  ) { }

  getUserByUsername(username: string): Observable<User> {
    return this.httpClient.get<User>(environment.API_URL + 'user/' + username, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getAllWorkers(): Observable<Worker[]> {
    return this.httpClient.get<Worker[]>(environment.API_URL + 'workers', {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getWorker(id: number): Observable<Worker> {
    return this.httpClient.get<Worker>(environment.API_URL + 'worker/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  addWorker(worker: Worker): Observable<Worker> {
    return this.httpClient.post<Worker>(environment.API_URL + 'worker', worker, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
  
  updateWorker(id: number, worker: Worker): Observable<Worker> {
    return this.httpClient.put<Worker>(environment.API_URL + 'worker/' + id, worker, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
  
  deleteWorker(id: number): Observable<void> {
    return this.httpClient.delete<void>(environment.API_URL + 'worker/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getWorkersByCenter(id: number): Observable<Worker[]> {
    return this.httpClient.get<Worker[]>(environment.API_URL + 'workers/center/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
  
  getAllClients(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(environment.API_URL + 'clients', {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
  
  getClient(id: number): Observable<Client> {
    return this.httpClient.get<Client>(environment.API_URL + 'client/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  addClient(client: Client): Observable<Client> {
    return this.httpClient.post<Client>(environment.API_URL + 'client', client, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
  
  updateClient(id: number, client: Client): Observable<Client> {
    return this.httpClient.put<Client>(environment.API_URL + 'client/' + id, client, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  deleteClient(id: number): Observable<void> {
    return this.httpClient.delete<void>(environment.API_URL + 'client/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }
  
  getClientsByCenter(id: number): Observable<Client[]> {
    return this.httpClient.get<Client[]>(environment.API_URL + 'clients/center/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }


  /* CENTERS */
  getAllCenters(): Observable<Center[]> {
    return this.httpClient.get<Center[]>(environment.API_URL + 'centers', {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

  getCenter(id: number): Observable<Center> {
    return this.httpClient.get<Center>(environment.API_URL + 'center/' + id, {headers: {'Authorization': `Bearer ${this.storageService.getCurrentToken()}`}})
  }

}
