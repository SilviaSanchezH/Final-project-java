import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Session } from 'src/app/auth/models/session.model';
import { StorageService } from 'src/app/auth/storage.service';
import { ContactService } from 'src/app/contacts/contact.service';
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

  center: Center = {
    name: "Mi centrito"
  } as Center;

  loggedUser: Session;

  activitiesList: Activity[] = [
    {
      id: 1,
      title: "Caminata",
      description: "Caminata por el campito",
      center: 1, 
      date: "19/03/2021",
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
      date: "19/03/2021",
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
      date: "19/03/2021",
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
      date: "19/03/2021",
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
      date: "19/03/2021",
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
      date: "19/03/2021",
      time: "17:00",
      type: "LOCAL_TOUR",
      users: [],
      workers: []
    }
  ];

  constructor(
    private readonly activityService: ActivityService, 
    private readonly storageService: StorageService, 
    private readonly contactService: ContactService,
    private dialog: MatDialog
    ) { }

  ngOnInit(): void {
    this.loggedUser = this.storageService.getCurrentSession();
    //this.getActivities();
    //this.getCenter();
  }

  getCenter() {
    this.contactService.getCenter(this.loggedUser?.center).subscribe(center => {
      this.center = center;
    })
  }

  getActivities() {
    this.activityService.getActivitiesByCenter(this.loggedUser?.center).subscribe(activities => {
      this.activitiesList = activities;
    });
  }

  //TODO: FALTA METODO AÑADIR CON SU BOTÓN

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

}
