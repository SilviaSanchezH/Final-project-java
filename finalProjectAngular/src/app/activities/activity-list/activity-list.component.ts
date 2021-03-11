import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Session } from 'src/app/auth/models/session.model';
import { StorageService } from 'src/app/auth/storage.service';
import { ContactService } from 'src/app/contacts/contact.service';
import { ActivityDetailsComponent } from 'src/app/dialogs/activity-details/activity-details.component';
import { ConfirmationDialogComponent } from 'src/app/dialogs/confirmation-dialog/confirmation-dialog.component';
import { EditActivityComponent } from 'src/app/dialogs/edit-activity/edit-activity.component';
import { Activity } from 'src/app/models/activity';
import { Center } from 'src/app/models/center';
import { ActivityService } from '../activity.service';

@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
})
export class ActivityListComponent implements OnInit {

  center: Center/*  = {
    name: "Mi centrito"
  } as Center */;

  loggedUser: Session;
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

  activitiesList: Activity[];/* = [
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "2021/03/08",
      time: "7:00 PM",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    },
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "2021/03/08",
      time: "17:00",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    },
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "2021/03/08",
      time: "17:00",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    },
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "2021/03/08",
      time: "17:00",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    },
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "2021/03/08",
      time: "17:00",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    },
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "2021/03/08",
      time: "17:00",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    }
  ] */

  constructor(
    private readonly activityService: ActivityService, 
    private readonly storageService: StorageService, 
    private readonly contactService: ContactService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar
    ) { }

  ngOnInit(): void {
    this.loggedUser = this.storageService.getCurrentSession();
    this.getActivities();
    this.getCenter();
  }

  getCenter() {
    this.contactService.getCenter(this.loggedUser?.center).subscribe(center => {
      this.center = center;
    }, error => {
      if(error.status === 401) {
        this.storageService.logout()
      }
    })
  }

  getActivities() {
    this.activityService.getActivitiesByCenter(this.loggedUser?.center).subscribe(activities => {
      this.activitiesList = activities; 
    }, error => {
      if(error.status === 401) {
        this.storageService.logout()
      }
    });
  }

  addActivity(){
    const dialogRef = this.dialog.open(EditActivityComponent, {
      width: '600px',
      data: { title: 'Añadir actividad', body: undefined },
      disableClose: true
    });
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.getActivities();
        }
    });
  }

  editActivity(activity: Activity) {
    const dialogRef = this.dialog.open(EditActivityComponent, {
      width: '600px',
      data: { title: 'Editar ' + activity.title, body: activity },
      disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getActivities();
      }
    });
  }

  goToActivity(activity: Activity) {
    if(this.loggedUser?.role === 'WORKER'){
      this.activityService.addWorkerToActivity(activity.id, this.loggedUser?.id).subscribe(() => {
        this.openSnackBar("Te has apuntado a la actividad :D", "OK");
        this.getActivities();
      }, error => {
        if(error.status === 401) {
          this.storageService.logout()
        }
      });
    }else{
      this.activityService.addClientToActivity(activity.id, this.loggedUser?.id).subscribe(() => {
        this.openSnackBar("Te has apuntado a la actividad :D", "OK");
        this.getActivities();
      }, error => {
        if(error.status === 401) {
          this.storageService.logout()
        }
      });
    }
  }

  openActivity(activity: Activity) {
    this.dialog.open(ActivityDetailsComponent, {
      width: '600px',
      data: { title: activity.title, body: activity }
    });
  }

  deleteActivity(activity: Activity) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: { title: 'Eliminar ' + activity.title, body: '¿Está seguro de que desea eliminar la actividad?' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.activityService.deleteActivity(activity.id).subscribe(() => {
          this.getActivities();
        }, error => {
          if(error.status === 401) {
            this.storageService.logout()
          }
        });
      }
    });
  }

  disableGoToActivity(activity: Activity): boolean {
    if(this.loggedUser.role === "WORKER") return activity.workers.find(wId => wId === this.loggedUser.id) !== undefined;
    else return activity.users.find(uId => uId === this.loggedUser.id) !== undefined;
  }

  openSnackBar(message: string, buttonMsg: string) {
    this._snackBar.open(message, buttonMsg, {
      duration: 3000,
      verticalPosition: 'bottom'
    });
  }

  getTypeViewValue(value: string): string {
    return this.types.find(type => type.value === value).viewValue;
  }

  getImageByType(value: string): string {
    switch(value) {
      case 'LOCAL_TOUR':
        return "../../../assets/activity-types/busbien.png";
      case 'INSIDE':
        return "../../../assets/activity-types/descarga.jpg";
      case 'TRIP':
        return "../../../assets/activity-types/bien.png";
      default:
        return "../../../assets/activity-types/trip.png";
    }
  }

}
