import {Output, EventEmitter, Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user.model';
import { RoleName, roles, Roles } from 'src/app/model/roles.model';
import { requiredValidator, emailValidator, phoneValidator } from 'src/app/util/custom-validator';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { ToastrService } from 'ngx-toastr';
import { PrettyCheckBoxChange } from 'ngx-pretty-checkbox';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { viLocale } from 'ngx-bootstrap/locale';

defineLocale('vi', viLocale);

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User = new User();
  userForm : FormGroup;
  roles = roles;

  @ViewChild(SwalComponent, {static: false}) deleteSwal: SwalComponent;
  
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _userService: UserService,
    private formBuider: FormBuilder,
    private toastr: ToastrService,
    private bsLocaleService: BsLocaleService
  ) { }

  ngOnInit() {
    // kiểm tra đường dẫn '/users/:id'
    this.route.paramMap.subscribe((params: ParamMap) => {

      // Tìm USER với id sử dụng Sync (Đồng bộ)
      this._userService.findByIdSync(params.get('id'))

        // dữ liệu trả về hợp lệ (có dữ liệu với ':id') thì hiển thị form update
        .then((data: User) => {
          this.user = data;
        })

        // dữ liệu trả về không hợp lệ (không có dữ liệu với ':id') thì chuyển hướng về 404 (không tìm thấy)
        .catch(err => this.router.navigate(['/404']));   
        this.createForm();
    })
  }

  createForm(){
    this.userForm = this.formBuider.group({
      email: ['', [requiredValidator(), emailValidator()]],
      phone: ['', [phoneValidator()]],
      address: ['', [requiredValidator()]],
      fullname: ['', [requiredValidator()]]
    });

    this.bsLocaleService.use('vi');
  }

  onRolesChange(id: number){
    console.log(this.userForm.controls.email);
    let rolename: RoleName = id == 0 ? RoleName.ROLE_CUSTOMER : id == 1 ? RoleName.ROLE_ADMIN : RoleName.ROLE_MANAGER ;
    this.user.roles = [
      new Roles(id, rolename)
    ];
  }

  onSubmit(){
    console.log(this.userForm.controls);
    if(!this.userForm.valid){
      this.toastr.error('Thông báo!', 'Vui lòng nhập chính xác thông tin!',{
        positionClass: 'toast-top-right'
      });
      return;
    }
   
    this._userService.update(this.user).subscribe(data => {       
      this.user = data;     
      this.resetForm(this.user);   
      this.toastr.success('Thông báo!', 'Cập nhật thông tin thành công!',{
        positionClass: 'toast-top-right'
      });
    });
  }

  resetForm(user: User){
    Object.keys(this.userForm.controls).forEach(elm => {
      this.userForm.controls[elm].reset(this.user[elm]);
    });
  }

  getGender($event){
    this.user.gender = $event.value;
  }

  onChangeBirthday(date: Date){
    this.user.birthday = date;
  }
}
