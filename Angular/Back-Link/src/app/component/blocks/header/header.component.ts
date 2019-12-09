import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userinfo: User = new User();
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _userService: UserService,
  ) { }

  ngOnInit() {
    this._userService.getinfo().subscribe(res => {
      this.userinfo = res;
    }, error => {
      this.router.navigate(['/not-found'])
    });
  }

}
