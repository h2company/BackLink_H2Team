import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-point',
  templateUrl: './list-point.component.html',
  styleUrls: ['./list-point.component.css']
})
export class ListPointComponent implements OnInit {
  id: string;
  listUser: User[];
  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _userService: UserService,
    private toastr: ToastrService) { }

  ngOnInit() {
    this._userService.findAll().subscribe(data => this.listUser = data);
  }

  ublockPoint(id) {
    this._userService.findByIdSync(id)

      // dữ liệu trả về hợp lệ (có dữ liệu với ':id') thì hiển thị form update
      .then((data: User) => {
        this.user = data;

        // Cập nhật điểm
        this.user.point = data.point + data.lockpoint;
        this.user.lockpoint = 0;


        this._userService.update(this.user).subscribe(data => {
          this.toastr.success('Thông báo!', 'Mở điểm khóa thành công!', {
            positionClass: 'toast-top-right'
          });
          this._userService.findAll().subscribe(data => this.listUser = data);
        });
      })

      // dữ liệu trả về không hợp lệ (không có dữ liệu với ':id') thì chuyển hướng về 404 (không tìm thấy)
      .catch(err => this.router.navigate(['/not-found']));
  }
}
