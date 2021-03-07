import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivityListComponent } from 'src/app/activities/activity-list/activity-list.component';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<ActivityListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { title: string, body: string }) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
