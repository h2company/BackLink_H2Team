import { FormControl, FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { requiredValidator, emailValidator } from "src/app/util/custom-validator";
import { UserService } from 'src/app/service/user.service';
import { of } from 'rxjs';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userFormGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder, 
    private _userService: UserService, 
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.createForm();
  }
  createForm() {
    this.userFormGroup = this.formBuilder.group({
      username: ['', [requiredValidator()]],
      password: ['', [requiredValidator()]]
    });
  }
  onSubmit() {
    console.log('submit');
    const data = {
      usernameOrEmail: this.userFormGroup.controls.username.value,
      password: this.userFormGroup.controls.password.value
    };
    this._userService.login(data).subscribe(res => {
      localStorage.setItem('oauth', res.accessToken);
      console.log(this._userService.getinfo().subscribe(data => {
        console.log(data);
      }));
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }
}
