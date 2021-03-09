import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { StorageService } from 'src/app/auth/storage.service';
import { ContactListComponent } from 'src/app/contacts/contact-list/contact-list.component';
import { ContactService } from 'src/app/contacts/contact.service';
import { Center } from 'src/app/models/center';
import { Client } from 'src/app/models/client';
import { User } from 'src/app/models/user';
import { Worker } from 'src/app/models/worker';

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent implements OnInit {

  contactForm: FormGroup
  selectedType: string;

  centerList: Center[] = [
    {
      id: 1,
      name: "OWO"
    }
  ] as Center[];
  selectedCenter: number;

  constructor(
    public dialogRef: MatDialogRef<ContactListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {title: string, body: User},
    private fb: FormBuilder,
    private readonly contactService: ContactService,
    private readonly storageService: StorageService
  ) { }

  ngOnInit(): void {
    this.contactService.getAllCenters().subscribe(center => this.centerList = center);
    this.buildForm();
  }

  buildForm() {
    if(this.data.body instanceof Client) {
      this.selectedType = "CLIENT";
      this.contactForm = this.fb.group({
        name: [this.data.body?.name || '', [Validators.required]],
        lastname: [this.data.body?.lastname || '', [Validators.required]],
        phoneNumber: [this.data.body?.phoneNumber || '', [Validators.required]],
        center: [this.data.body?.center || '', [Validators.required]],
        address: [this.data.body?.address || '', [Validators.required]],
        city: [this.data.body?.city || '', [Validators.required]],
        username: [this.data.body?.username || '', Validators.required],
        password: [this.data.body?.password || '', [Validators.required, Validators.minLength(4)]]
      })
    } else if(this.data.body instanceof Worker) {
      this.selectedType = "WORKER";
      this.contactForm = this.fb.group({
        name: [this.data.body?.name || '', [Validators.required]],
        lastname: [this.data.body?.lastname || '', [Validators.required]],
        phoneNumber: [this.data.body?.phoneNumber || '', [Validators.required]],
        center: [this.data.body?.center || '', [Validators.required]],
        professionalNumber: [this.data.body?.professionalNumber || '', [Validators.required]],
        occupation: [this.data.body?.occupation || '', [Validators.required]],
        username: [this.data.body?.username || '', Validators.required],
        password: [this.data.body?.password || '', [Validators.required, Validators.minLength(4)]]
      })
    }
    this.selectedCenter = this.data.body.center;

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {
    console.log(this.contactForm);
    if(this.contactForm.valid) {
      if(this.data.body?.id) {
        switch(this.selectedType) {
          case 'WORKER': 
            const worker: Worker = {
              ...this.contactForm.value,
              id: this.data.body.id
            }
            console.log(worker);
            this.contactService.updateWorker(this.data.body?.id, worker).subscribe((response: Worker) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
            })
            break;
          case 'CLIENT': 
            const client: Client = {
              ...this.contactForm.value,
              id:this.data.body.id
            };
            console.log(client);
            this.contactService.updateClient(this.data.body?.id, client).subscribe((response: Client) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
            });
            break;
        }
      }else{
        switch(this.selectedType) {
          case 'WORKER':
            console.log(this.contactForm.value);
            this.contactService.addWorker(this.contactForm.value).subscribe((response: Worker) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
            })
            break;
          case 'CLIENT':
            console.log(this.contactForm.value);
            this.contactService.addClient(this.contactForm.value).subscribe((response: Client) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
            });
            break;
        }
      }
    }
  }

  get name(){
    return this.contactForm.get("name")
  }

  get lastname(){
    return this.contactForm.get("lastname")
  }

  get phoneNumber(){
    return this.contactForm.get("phoneNumber")
  }

  get center(){
    return this.contactForm.get("center")
  }

  get address(){
    return this.contactForm.get("address")
  }

  get city(){
    return this.contactForm.get("city")
  }

  get professionalNumber() {
    return this.contactForm.get("professionalNumber")
  }

  get occupation() {
    return this.contactForm.get("occupation")
  }

  get username() {
    return this.contactForm.get("username")
  }

  get password() {
    return this.contactForm.get("password")
  }


}
