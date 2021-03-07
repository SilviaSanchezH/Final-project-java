import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/auth/storage.service';

@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
})
export class ActivityListComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
