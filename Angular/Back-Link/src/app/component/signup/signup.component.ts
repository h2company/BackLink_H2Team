import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { requiredValidator, emailValidator, minmaxValidator, lengthValidator, phoneValidator } from 'src/app/util/custom-validator';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  userFormGroup: FormGroup;
  errApi = {
    username : {status: false, message : ''},
    email: {status: false, message : ''},
    phone: {status: false, message : ''}
  }
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
      fullname: ['', [requiredValidator(), lengthValidator(5,30)]],
      email: ['', [requiredValidator(), emailValidator()]],
      phone: ['', [requiredValidator(), phoneValidator()]],
      username: ['', [requiredValidator(), lengthValidator(5,30)]],
      password: ['', [requiredValidator(), lengthValidator(8,50)]]
    });
  }

  removeError($event){
    this.errApi[$event.target.id].status = false;
    this.errApi[$event.target.id].message = '';
  }

  clearErrorAPI(){    
    Object.keys(this.errApi).forEach(e => {
      this.errApi[e].status = false;
      this.errApi[e].message = '';
    })  
  }

  handleErrorAPI(errs){    
    Object.keys(errs.error.errors).forEach(e => {
      this.errApi[e].status = true;
      this.errApi[e].message = errs.error.errors[e];
    })  
  }

  onSubmit() {
    console.log('submit');
    const data: User = new User();
    data.fullname = this.userFormGroup.controls.fullname.value;
    data.email = this.userFormGroup.controls.email.value;
    data.username = this.userFormGroup.controls.username.value;
    data.password = this.userFormGroup.controls.password.value;
    data.phone = this.userFormGroup.controls.phone.value;
    this._userService.register(data).subscribe(res => {
        this.toastr.success('Thông báo', res.message, {
          positionClass: 'toast-top-right'
        });
        this.router.navigate(['/dashboard']);
    }, errs => {
      this.handleErrorAPI(errs);
      this.toastr.error("Thông báo", errs.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
}
