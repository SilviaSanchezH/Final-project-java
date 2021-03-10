import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivityListComponent } from 'src/app/activities/activity-list/activity-list.component';
import { ActivityService } from 'src/app/activities/activity.service';
import { Activity } from 'src/app/models/activity';
import { Client } from 'src/app/models/client';
import { Worker } from 'src/app/models/worker';

@Component({
  selector: 'app-activity-details',
  templateUrl: './activity-details.component.html',
  styleUrls: ['./activity-details.component.css']
})
export class ActivityDetailsComponent implements OnInit {

  clients: Client[];
  workers: Worker[];

  constructor(
    public dialogRef: MatDialogRef<ActivityListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { title: string, body: Activity },
    private readonly activityService: ActivityService
    ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.activityService.getClientsByActivity(this.data.body.id).subscribe(clients => this.clients = clients);
    this.activityService.getWorkersByActivity(this.data.body.id).subscribe(workers => this.workers = workers);
  }
}
