import { Component, OnInit } from '@angular/core';
import { StorageService } from '../auth/storage.service';

@Component({
  selector: 'app-navigator',
  templateUrl: './navigator.component.html',
  styleUrls: ['./navigator.component.css']
})
export class NavigatorComponent {

  constructor(private storageService:StorageService) { }

  isAuthenticated(): boolean {
    return this.storageService.isAuthenticated()
  }

}
