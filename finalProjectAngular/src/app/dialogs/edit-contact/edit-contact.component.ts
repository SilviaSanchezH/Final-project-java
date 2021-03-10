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
import { CustomValidators } from 'src/app/utils/custom-validators';

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent implements OnInit {

  contactForm: FormGroup
  selectedType: string;
  selectedGender: string;

  genders = [
    {
      value: 'FEMALE',
      viewValue: 'Femenino'
    },
    {
      value: 'MALE',
      viewValue: 'Masculino'
    }
  ]

  centerList: Center[] /* = [
    {
      id: 1,
      name: "OWO"
    }
  ] as Center[] */;
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
        name: [this.data.body?.name || '', [Validators.required, CustomValidators.nameValidator]],
        lastName: [this.data.body?.lastName || '', [Validators.required, CustomValidators.nameValidator]],
        secondSurname: [this.data.body?.secondSurname || '', [CustomValidators.nameValidator]],
        age: [this.data.body?.age || '', [Validators.required]],
        phoneNumber: [this.data.body?.phoneNumber || '', [Validators.required, CustomValidators.phoneValidator]],
        center: [this.data.body?.center || '', [Validators.required]],
        address: [this.data.body?.address || '', [Validators.required]],
        city: [this.data.body?.city || '', [Validators.required]],
        username: [this.data.body?.username || '', Validators.required],
        password: [this.data.body?.password || '', [Validators.required, Validators.minLength(4)]],
        gender: [this.data.body?.gender || '', [Validators.required]]
      })
    } else if(this.data.body instanceof Worker) {
      this.selectedType = "WORKER";
      this.contactForm = this.fb.group({
        name: [this.data.body?.name || '', [Validators.required]],
        lastName: [this.data.body?.lastName || '', [Validators.required]],
        secondSurname: [this.data.body?.secondSurname || '' ,[CustomValidators.nameValidator]],
        phoneNumber: [this.data.body?.phoneNumber || '', [Validators.required]],
        center: [this.data.body?.center || '', [Validators.required]],
        professionalNumber: [this.data.body?.professionalNumber || '', [Validators.required]],
        occupation: [this.data.body?.occupation || '', [Validators.required]],
        username: [this.data.body?.username || '', Validators.required],
        password: [this.data.body?.password || '', [Validators.required, Validators.minLength(4)]],
        gender: [this.data.body?.gender || '', [Validators.required]],
      })
    }
    this.selectedCenter = this.data.body.center;
    this.selectedGender = this.data.body.gender;

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {
    if(this.contactForm.valid) {
      if(this.data.body?.id) {
        switch(this.selectedType) {
          case 'WORKER': 
            const worker: Worker = {
              ...this.contactForm.value,
              id: this.data.body.id
            }
            this.contactService.updateWorker(this.data.body?.id, worker).subscribe((response: Worker) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
              if(error.status === 401) {
                this.storageService.logout()
              }
            })
            break;
          case 'CLIENT': 
            const client: Client = {
              ...this.contactForm.value,
              id:this.data.body.id
            };
            this.contactService.updateClient(this.data.body?.id, client).subscribe((response: Client) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
              if(error.status === 401) {
                this.storageService.logout()
              }
            });
            break;
        }
      }else{
        switch(this.selectedType) {
          case 'WORKER':
            this.contactService.addWorker(this.contactForm.value).subscribe((response: Worker) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
              if(error.status === 401) {
                this.storageService.logout()
              }
            })
            break;
          case 'CLIENT':
            this.contactService.addClient(this.contactForm.value).subscribe((response: Client) => {
              this.dialogRef.close(true);
            }, error => {
              console.log(error);
              if(error.status === 401) {
                this.storageService.logout()
              }
            });
            break;
        }
      }
    }
  }

  get name(){
    return this.contactForm.get("name")
  }

  get lastName(){
    return this.contactForm.get("lastName")
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

  get age() {
    return this.contactForm.get("age")
  }

  get gender() {
    return this.contactForm.get("gender")
  }

  get secondSurname() {
    return this.contactForm.get("secondSurname")
  }


}
