import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { requiredValidator, emailValidator } from 'src/app/util/custom-validator';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  userFormGroup: FormGroup;
  constructor(
    private formBuilder: FormBuilder, 
    private _userService: UserService, 
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.createForm();
  }
  createForm() {
    this.userFormGroup = this.formBuilder.group({
      fullname: ['', [requiredValidator()]],
      email: ['', [requiredValidator(), emailValidator()]],
      username: ['', [requiredValidator()]],
      password: ['', [requiredValidator()]]
    });
  }
  onSubmit() {
    console.log('submit');
    const data: User = new User();
    data.fullname = this.userFormGroup.controls.fullname.value;
    data.email = this.userFormGroup.controls.email.value;
    data.username = this.userFormGroup.controls.username.value;
    data.password = this.userFormGroup.controls.password.value;
    this._userService.register(data).subscribe(res => {
        this.toastr.success('Thông báo', res.message, {
          positionClass: 'toast-top-right'
        });
        this.router.navigate(['/dashboard']);
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }
}
