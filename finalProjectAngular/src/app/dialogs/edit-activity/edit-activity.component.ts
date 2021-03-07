import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivityListComponent } from 'src/app/activities/activity-list/activity-list.component';
import { ActivityService } from 'src/app/activities/activity.service';
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

  constructor(
    public dialogRef: MatDialogRef<ActivityListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { title: string, body: Activity },
    private fb: FormBuilder,
    private readonly activityService: ActivityService
    ) { }

    ngOnInit () {
      this.buildForm();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }


  buildForm() {
    this.activityForm = this.fb.group({
      title: [this.data.body?.title || '', [Validators.required]],
      description: [this.data.body?.description || '', [Validators.required]],
      type: [this.data.body?.type || '', [Validators.required]],
      date: [this.data.body?.date || '', [Validators.required]],
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
        this.activityService.addActivity(this.activityForm.value).subscribe((response: Activity) => {
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
}
