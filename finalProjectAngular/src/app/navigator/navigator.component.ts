import { Component, OnInit } from '@angular/core';
import { StorageService } from '../auth/storage.service';
import { ContactService } from '../contacts/contact.service';
import { Center } from '../models/center';

@Component({
  selector: 'app-navigator',
  templateUrl: './navigator.component.html',
  styleUrls: ['./navigator.component.css']
})
export class NavigatorComponent implements OnInit{

  center: Center;

  constructor(private storageService:StorageService, private readonly contactService: ContactService) { }

  ngOnInit() {
    this.contactService.getCenter(this.storageService.getCurrentSession()?.center).subscribe(center => this.center = center);
  }

  isAuthenticated(): boolean {
    return this.storageService.isAuthenticated()
  }

}
