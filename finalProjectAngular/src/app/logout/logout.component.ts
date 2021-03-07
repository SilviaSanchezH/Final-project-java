import { Component, OnInit } from '@angular/core';
import { StorageService } from '../auth/storage.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent{


  constructor(private storageService:StorageService) { }

  isAuthenticated(): boolean {
    return this.storageService.isAuthenticated()
  }

  logout() {
    this.storageService.logout()
  }
}
