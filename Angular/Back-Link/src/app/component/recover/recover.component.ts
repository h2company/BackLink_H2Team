import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { requiredValidator, emailValidator } from 'src/app/util/custom-validator';

@Component({
  selector: 'app-recover',
  templateUrl: './recover.component.html',
  styleUrls: ['./recover.component.css']
})
export class RecoverComponent implements OnInit {
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
      email: ['', [requiredValidator(), emailValidator()]]
    });
  }
  onSubmit() {
    console.log('vv');
    const data = {
      email: this.userFormGroup.controls.email.value
    };
    this._userService.recover(data).subscribe(res => {
      this.toastr.success("Thành công !", "Vui lòng kiểm tra email của bạn !", {
        positionClass: 'toast-top-right'
      });
      this.router.navigate(['/dashboard']);
    }, error => {
      console.log(error);
      this.toastr.error("Thông báo !", "Dữ liệu không hợp lệ !", {
        positionClass: 'toast-top-right'
      });
    });
  }
}