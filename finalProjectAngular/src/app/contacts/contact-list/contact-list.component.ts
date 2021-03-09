import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatAccordion } from '@angular/material/expansion';
import { ActivityService } from 'src/app/activities/activity.service';
import { Session } from 'src/app/auth/models/session.model';
import { StorageService } from 'src/app/auth/storage.service';
import { ConfirmationDialogComponent } from 'src/app/dialogs/confirmation-dialog/confirmation-dialog.component';
import { EditContactComponent } from 'src/app/dialogs/edit-contact/edit-contact.component';
import { Center } from 'src/app/models/center';
import { Client } from 'src/app/models/client';
import { Worker } from 'src/app/models/worker';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  loggedUser: Session;
  center: Center;

  @ViewChild(MatAccordion) accordion: MatAccordion;

  constructor(
    private readonly activityService: ActivityService, 
    private readonly storageService: StorageService, 
    private readonly contactService: ContactService,
    private dialog: MatDialog
  ) { }

  

  ngOnInit(): void {
    this.loggedUser = this.storageService.getCurrentSession();
    // this.getCenter();
    // this.getAllClients();
    // this.getAllWorkers();
  }

  
  workerList: Worker[] = [
  {
    id: 1,
    username: 'paco',
    password: '1234',
    name: 'paco',
    lastname: 'sanchez uwuwuwuwuuuwuwuw',
    phoneNumber: '987654332',
    center: 2,
    role: {
      id: 1,
      name: 'WORKER',
    },
    occupation: 'enfermero',
    professionalNumber: '89',
  },
  {
    id: 2,
    username: 'paco',
    password: '1234',
    name: 'paco',
    lastname: 'sanchez',
    phoneNumber: '987654332',
    center: 2,
    role: {
      id: 1,
      name: 'WORKER',
    },
    occupation: 'enfermero',
    professionalNumber: '89',
  },
  {
    id: 3,
    username: 'paco',
    password: '1234',
    name: 'paco',
    lastname: 'sanchez',
    phoneNumber: '987654332',
    center: 2,
    role: {
      id: 1,
      name: 'WORKER',
    },
    occupation: 'enfermero',
    professionalNumber: '89',
  }
  ];

  clientList: Client[] = [
    {
      id: 4,
      username: 'paco',
      password: '1234',
      name: 'paco',
      lastname: 'sanchez',
      phoneNumber: '987654332',
      center: 1,
      role: {
        id: 1,
        name: 'CLIENT',
      },
      address: 'Calle alcalá',
      city: 'Madrid',
    },
    {
      id: 5,
      username: 'paco',
      password: '1234',
      name: 'paco',
      lastname: 'sanchez',
      phoneNumber: '987654332',
      center: 1,
      role: {
        id: 1,
        name: 'CLIENT',
      },
      address: 'Calle alcalá',
      city: 'Madrid',
    },
    {
      id: 6,
      username: 'paco',
      password: '1234',
      name: 'paco',
      lastname: 'sanchez',
      phoneNumber: '987654332',
      center: 1,
      role: {
        id: 1,
        name: 'CLIENT',
      },
      address: 'Calle alcalá',
      city: 'Madrid',
    }
    ];

  getCenter() {
    this.contactService.getCenter(this.loggedUser?.center).subscribe(center => this.center = center)
  }

  getAllWorkers() {
    this.contactService.getWorkersByCenter(this.loggedUser?.center).subscribe(workers => this.workerList = workers)
  }

  getAllClients() {
    this.contactService.getClientsByCenter(this.loggedUser?.center).subscribe(clients => this.clientList = clients)
  }

  addClient() {
    const dialogRef = this.dialog.open(EditContactComponent, {
      width: '600px',
      data: { title: 'Nuevo usuario', body: new Client() },
      disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllClients();
      }
    });
  }

  editClient(client: Client) {
    const dialogRef = this.dialog.open(EditContactComponent, {
      width: '600px',
      data: { title: 'Editar a' + ' ' + client.name + ' ' + client.lastname, body: new Client(client) },
      disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllClients();
      }
    });
  }

  deleteClient(client: Client) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: { title: 'Eliminar a ' + client.name + ' ' + client.lastname, body: '¿Está seguro de que desea eliminar a este usuario?' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.contactService.deleteClient(client.id).subscribe(() => {
          this.getAllClients;
        }, error => {
          if(error.status === 401) {
            this.storageService.logout()
          }
        });
      }
    });
  }

  addWorker() {
    const dialogRef = this.dialog.open(EditContactComponent, {
      width: '600px',
      data: { title: 'Nuevo profesional', body: new Worker() },
      disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllWorkers();
      }
    });
  }

  editWorker(worker: Worker) {
    const dialogRef = this.dialog.open(EditContactComponent, {
      width: '600px',
      data: { title: 'Editar ' + worker.name + worker.lastname, body: new Worker(worker) },
      disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllWorkers();
      }
    });
  }

  deleteWorker(worker: Worker) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: { title: 'Eliminar a ' + worker.name + ' ' + worker.lastname, body: '¿Está seguro de que desea eliminar a este usuario?' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.contactService.deleteClient(worker.id).subscribe(() => {
          this.getAllWorkers;
        }, error => {
          if(error.status === 401) {
            this.storageService.logout()
          }
        });
      }
    });
  }

  getFullName(name: string, lastName: string): string {
    const nameLength = name.length;
    let displayName = name.slice(0, 20) + " " + lastName.slice(0, 20 - (nameLength + lastName.length));
    return nameLength + lastName.length > 20 ? displayName + "..." : displayName;
  }

  getRandomAvatar(seed: string): string {
    return `https://avatars.dicebear.com/api/bottts/${seed}.svg`;
  }

}
