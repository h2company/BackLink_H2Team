import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { requiredValidator, emailValidator, phoneValidator } from 'src/app/util/custom-validator';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  user: User = new User();
  selectedFile: File;
  userForm: FormGroup;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _userService: UserService,
    private formBuider: FormBuilder,
    private toastr: ToastrService,
    private _authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    this._userService.getinfo().subscribe(res => {
      this.user = res;
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
      this.router.navigate(['/not-found'])
    });
    this.createForm();
  }

  createForm() {
    this.userForm = this.formBuider.group({
      username: ['', [requiredValidator()]],
      email: ['', [requiredValidator(), emailValidator()]],
      phone: ['', [phoneValidator()]],
      address: ['', [requiredValidator()]],
      fullname: ['', [requiredValidator()]]
    });
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
      this.toastr.success('Thông báo!', 'Cập nhật thông tin thành công!',{
        positionClass: 'toast-top-right'
      });
    });
  }
  
  getGender($event) {
    this.user.gender = $event.value;
  }

  onChangeBirthday(date: Date) {
    this.user.birthday = date;
  }

  onFileChanged(event){
    this.selectedFile = event.target.files[0];
    if(!this.selectedFile){
      return;
    }
    const uploadData = new FormData();
    uploadData.append('file', this.selectedFile, this.selectedFile.name);
    this._userService.avatar(uploadData).subscribe(event => {
        this.user.avatar = event.avatar; // handle event here

        this._userService.getinfo().subscribe(res => {
          let _data = Object.keys(res);
          _data.forEach(element => {
            sessionStorage.setItem(element, res[element]);
          });
        });

        this._authenticationService.avatar.next(event.avatar);
      });
  }
}