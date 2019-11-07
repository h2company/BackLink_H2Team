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
  userForm: FormGroup;
  roles = roles;
  controls: any;
  hide: boolean = true;
  text: string = 'Hiện';
  errApi = {
    username : {status: false, message : ''},
    email: {status: false, message : ''},
    phone: {status: false, message : ''}
  }
  isSubmit: boolean = false;

  @ViewChild(SwalComponent, { static: false }) deleteSwal: SwalComponent;

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
    this.defaultValue();
  }

  createForm() {
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

  defaultValue() {
    this.user.roles = [new Roles(0, RoleName.ROLE_CUSTOMER)];
    this.user.gender = true;
    // this.user.username = 'quoxank1';
    // this.user.email = 'qa1796@gmail.com';
    // this.user.fullname = 'Le Anh Quoc';
    // this.user.phone = '0377312609';
    // this.user.address = 'go vap';
    // this.user.password = 'asdasdasd';
  }

  onRolesChange(id: number) {
    let rolename: RoleName = id == 0 ? RoleName.ROLE_CUSTOMER : id == 1 ? RoleName.ROLE_ADMIN : RoleName.ROLE_MANAGER;
    this.user.roles = [
      new Roles(id, rolename)
    ];
  }

  onSubmit() {    
    this.clearErrorAPI();
    if (!this.userForm.valid) {      
    this.isSubmit = true;
      this.toastr.error('Thông báo!', 'Vui lòng nhập chính xác thông tin!', {
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
    }, errs => {
      this.handleErrorAPI(errs);
      this.toastr.error('Thông báo!', errs.error.message, {
        positionClass: 'toast-top-right'
      });
    });

  }

  showHide() {
    this.hide = !this.hide;
    this.text = this.hide ? 'Hiện' : 'Ẩn';
  }

  resetForm(user: User) {
    this.isSubmit = false;
    Object.keys(this.controls).forEach(elm => {
      this.controls[elm].reset(this.user[elm]);
    });
  }

  getGender($event){
    this.user.gender = $event.value;
  }

  onChangeBirthday(date: Date){
    this.user.birthday = date;
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
}
