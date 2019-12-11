import { FormControl, FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { requiredValidator, emailValidator } from "src/app/util/custom-validator";
import { UserService } from 'src/app/service/user.service';
import { of } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';


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
    private toastr: ToastrService,
    private router: Router
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
    if(!this.userFormGroup.valid){
      this.toastr.error("Thông báo", "Vui lòng nhập đầy đủ thông tin", {
        positionClass: 'toast-top-right'
      });
    }
    const data = {
      usernameOrEmail: this.userFormGroup.controls.username.value,
      password: this.userFormGroup.controls.password.value
    };
    this._userService.login(data).subscribe(res => {
      localStorage.setItem('oauth', res.accessToken);
      this._userService.getinfo().subscribe(res => {
        let _data = Object.keys(res);
        _data.forEach(element => {
          sessionStorage.setItem(element, res[element]);
        });
        this.router.navigate(['/dashboard']);
      });
    }, error => {
      this.toastr.error("Thông báo", "Tài khoản hoặc mật khẩu không đúng", {
        positionClass: 'toast-top-right'
      });
    });
  }
}
