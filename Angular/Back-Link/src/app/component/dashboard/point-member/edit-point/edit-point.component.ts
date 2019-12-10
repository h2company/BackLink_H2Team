import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';
import { requiredValidator, emailValidator, phoneValidator, minmaxValidator } from 'src/app/util/custom-validator';

@Component({
  selector: 'app-edit-point',
  templateUrl: './edit-point.component.html',
  styleUrls: ['./edit-point.component.css']
})
export class EditPointComponent implements OnInit {

  user: User = new User();
  userForm: FormGroup;
  id: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _userService: UserService,
    private formBuider: FormBuilder,
    private toastr: ToastrService) { }

  ngOnInit() {
    // kiểm tra đường dẫn '/users/:id'
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = params.get('id');
      // Tìm USER với id sử dụng Sync (Đồng bộ)
      this._userService.findByIdSync(params.get('id'))

        // dữ liệu trả về hợp lệ (có dữ liệu với ':id') thì hiển thị form update
        .then((data: User) => {
          this.user = data;
        })

        // dữ liệu trả về không hợp lệ (không có dữ liệu với ':id') thì chuyển hướng về 404 (không tìm thấy)
        .catch(err => this.router.navigate(['/not-found']));
      this.createForm();
    })
  }

  createForm(){
    this.userForm = this.formBuider.group({
      username: ['', [requiredValidator()]],
      fullname: ['', [requiredValidator()]],
      point: ['', [minmaxValidator(0,9999999)]]
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
      this.router.navigate(['/dashboard/point-member']);
    });
  }

  
}
