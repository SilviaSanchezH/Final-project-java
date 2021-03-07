import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LogInResponseDto } from '../dto/log-in-response.dto';
import { LoginService } from '../login.service';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup
  notExists: boolean = false
  error: boolean = false

  constructor(private loginService: LoginService, private storageService: StorageService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', Validators.required]
    })
  }

  logIn() {
    this.notExists = false; this.error = false
    if(this.loginForm.valid) {
      this.loginService.logIn(this.loginForm.value).subscribe((response: LogInResponseDto) => {
        if(response.token) {
          this.storageService.setCurrentSession(response)
          this.router.navigate(['/'])
        }
      }, error => {
        if(error.status === 401) this.notExists = true
        else this.error = true
      })
    }
  }


  get username() { return this.loginForm.get('username') }
  get password() { return this.loginForm.get('password') }
}
