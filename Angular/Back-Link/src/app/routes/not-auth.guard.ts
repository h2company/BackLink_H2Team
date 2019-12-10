import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { UserService } from '../service/user.service';

@Injectable({
  providedIn: 'root'
})
export class NotAuthGuard implements CanActivate {

  constructor(
    private authService: AuthenticationService, 
    private _userService: UserService,
    private router: Router
    ){}

  canActivate(): boolean {
    if(this.authService.isLogged()){
      this._userService.getinfo().subscribe(res => {
        let _data = Object.keys(res);
        _data.forEach(element => {
          sessionStorage.setItem(element, res[element]);
        });
        this.authService.avatar.next(res.avatar);
      });

      this.router.navigate(['/dashboard']);
      return false;
    }
    return true;
  }
  
}
