import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class NotAuthGuard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router){}

  canActivate(): boolean {
    if(!this.authService.isLogged()){
      this.router.navigate(['/signin']);
      return false;
    }
    return true;
  }
  
}
