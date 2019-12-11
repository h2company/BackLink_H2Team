import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user.model';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  listUser: User[];
  currentId = null;

  @ViewChild("deleteSwal", { static: false }) private deleteSwal: SwalComponent;

  constructor(
    private _userService: UserService,
    private toastr: ToastrService
    ) { }

  ngOnInit() {
    this.getList();
  }

  getList(){
    this._userService.findAll().subscribe(data => this.listUser = data);
  }

  deleteClick(id){
    this.currentId = id;
    this.deleteSwal.fire();
  }

  deleteUser(){
    this._userService.deleteById(this.currentId).subscribe(res => {
      this.getList();
      this.toastr.success('Thông báo!', 'Xóa thành công',{
        positionClass: 'toast-top-right'
      });
      this.currentId = null;
    })
  } 

}
