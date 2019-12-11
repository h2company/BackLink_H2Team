import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  user: User;

  constructor(private _userService: UserService) { }

  ngOnInit() {
    this._userService.getinfo().subscribe(res => {
      this.user = res;
    })
  }

}
