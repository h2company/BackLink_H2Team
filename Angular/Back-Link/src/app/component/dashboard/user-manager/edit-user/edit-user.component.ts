import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user.model';
import { roles } from 'src/app/model/roles.model';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User;
  userForm : FormGroup;
  roles = roles;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _userService: UserService,
    private formBuider: FormBuilder
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
      username: ['', [Validators.required]],
      email: ['', [Validators.required]],
      phone: ['', [Validators.required, Validators.min(10), Validators.max(10)]],
      address: ['', [Validators.required]],
      fullname: ['', [Validators.required]],
      updateAt: ['', [Validators.required]],
      createAt: ['', [Validators.required]],
    });
  }

}
