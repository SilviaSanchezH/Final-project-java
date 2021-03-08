import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivityListComponent } from 'src/app/activities/activity-list/activity-list.component';
import { ActivityService } from 'src/app/activities/activity.service';
import { StorageService } from 'src/app/auth/storage.service';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'app-edit-activity',
  templateUrl: './edit-activity.component.html',
  styleUrls: ['./edit-activity.component.css']
})
export class EditActivityComponent implements OnInit{

  activityForm: FormGroup;
  selectedType: string;
  types = [
    {
      value: 'LOCAL_TOUR', 
      viewValue: 'Excursión local'
    },
    {
      value: 'INSIDE', 
      viewValue: 'En el centro'
    },
    {
      value: 'TRIP', 
      viewValue: 'Viaje'
    }
  ]
  
  minDate: Date;
  selectedDate: Date;

  constructor(
    public dialogRef: MatDialogRef<ActivityListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { title: string, body: Activity },
    private fb: FormBuilder,
    private readonly activityService: ActivityService,
    private readonly storageService: StorageService
    ) { }

    ngOnInit () {
      this.minDate = new Date();
      this.buildForm();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  buildForm() {
    this.selectedDate = this.data.body?.date ? new Date(this.data.body?.date) : null;
    this.activityForm = this.fb.group({
      title: [this.data.body?.title || '', [Validators.required]],
      description: [this.data.body?.description || '', [Validators.required]],
      type: [this.data.body?.type || '', [Validators.required]],
      date: [this.selectedDate?.toISOString() || '', [Validators.required]],
      time: [this.data.body?.time || '', [Validators.required]],
    });
    this.selectedType = this.data.body?.type;
  }

  //TODO: FAlta date y time en HTML 
  submit() {
    if(this.activityForm.valid) {
      if(this.data.body?.id) {
        const activity: Activity = {
          ...this.activityForm.value,
          id: this.data.body.id,
          center: this.data.body.center,
          users: this.data.body.users,
          workers: this.data.body.workers
        };
        this.activityService.updateActivity(this.data.body.id, activity).subscribe((response: Activity) => {
          this.dialogRef.close(true);
        }, error => {
          console.log(error); 
        })
      } else {
        const activity: Activity = {
          ...this.activityForm.value,
          center: this.storageService.getCurrentSession()?.center,
          users: [],
          workers: []
        }
        this.activityService.addActivity(activity).subscribe((response: Activity) => {
          this.dialogRef.close(true);
        }, error => {
          console.log(error); 
        })
      }
    }
  }


  get title() {
    return this.activityForm.get("title")
  }

  get description() {
    return this.activityForm.get("description")
  }
  
  get type() {
    return this.activityForm?.get("type")
  }

  get date() {
    return this.activityForm?.get("date")
  }

  get time() {
    return this.activityForm?.get('time')
  }
}
