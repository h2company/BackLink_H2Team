import { Component, OnInit } from '@angular/core';
import { UserService } from './service/user.service';
import { User } from './model/user.model';
import { LoginComponent } from './component/login/login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public users: User[] = [];
  public isLogin = true;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    //get dữ liệu từ server
    //this.userService.findAll().subscribe(data => this.users = data, error => console.log(error));    
  }

}
