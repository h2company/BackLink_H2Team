import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  userinfo: User = new User();  
  avatar: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _userService: UserService,
    private _authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    this._userService.getinfo().subscribe(res => {
      this.userinfo = res;

      let _data = Object.keys(res);
      _data.forEach(element => {
        sessionStorage.setItem(element, res[element]);
      });

      this.avatar = sessionStorage.getItem("avatar");
    }, error => {
      this.router.navigate(['/not-found'])
    });    

    this._authenticationService.avatar.subscribe(item => {
      this.avatar = item;
    })
  }

}
