import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-recharge',
  templateUrl: './recharge.component.html',
  styleUrls: ['./recharge.component.css']
})
export class RechargeComponent implements OnInit {

  user: User = new User();
  router: any;
  constructor(private route: ActivatedRoute,
    private _userService: UserService
  ) { }

  ngOnInit() {
    this._userService.getinfo().subscribe(res => {
      this.user = res;
    }, error => {
      this.router.navigate(['/not-found'])
    });
  }

}
