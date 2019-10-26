import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  listUser: User[];

  constructor(private _userService: UserService) { }

  ngOnInit() {
    this._userService.findAll().subscribe(data => this.listUser = data);
  }

}
