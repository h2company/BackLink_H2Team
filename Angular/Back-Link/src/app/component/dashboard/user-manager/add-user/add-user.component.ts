import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { roles, RoleName, Roles } from 'src/app/model/roles.model';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';
import { requiredValidator, emailValidator, phoneValidator, lengthValidator, minmaxValidator } from 'src/app/util/custom-validator';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { viLocale } from 'ngx-bootstrap/locale';

defineLocale('vi', viLocale);

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user: User = new User();
  userForm : FormGroup;
  roles = roles;
  controls : any;
  hide: boolean = true;
  text: string =  'Hiện';

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
    this.createForm();
  }

  createForm(){
    this.userForm = this.formBuider.group({
      username: ['', [requiredValidator(), lengthValidator(8, 50)]],
      password: ['', [requiredValidator(), lengthValidator(8, 50)]],
      email: ['', [requiredValidator(), emailValidator()]],
      phone: ['', [phoneValidator()]],
      address: ['', [requiredValidator()]],
      fullname: ['', [requiredValidator()]]
    });
    this.controls = this.userForm.controls;
    this.bsLocaleService.use('vi');
  }

  onRolesChange(id: number){
    let rolename: RoleName = id == 0 ? RoleName.ROLE_CUSTOMER : id == 1 ? RoleName.ROLE_ADMIN : RoleName.ROLE_MANAGER ;
    this.user.roles = [
      new Roles(id, rolename)
    ];
  }

  onSubmit(){
    if(!this.userForm.valid){
      this.toastr.error('Thông báo!', 'Vui lòng nhập chính xác thông tin!',{
        positionClass: 'toast-top-right'
      });
      return;
    }
   
    this._userService.save(this.user).subscribe(data => {       
      this.user = data;     
      this.resetForm(this.user);   
      this.toastr.success('Thông báo!', 'Cập nhật thông tin thành công!', {
        positionClass: 'toast-top-right'
      })
    }, err => {
        this.toastr.error('Thông báo!', err.error.message,{
          positionClass: 'toast-top-right'
        });
    });
  }

  showHide(){
    this.hide = !this.hide;
    this.text = this.hide ? 'Hiện' : 'Ẩn';
  }

  resetForm(user: User){
    Object.keys(this.controls).forEach(elm => {
      this.controls[elm].reset(this.user[elm]);
    });
  }
}
