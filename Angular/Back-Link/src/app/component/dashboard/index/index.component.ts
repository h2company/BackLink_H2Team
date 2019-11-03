import { Component, OnInit } from '@angular/core';
import { BacklinkService } from 'src/app/service/backlink.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  backlinks = null;
  constructor(
    private _backlinkService: BacklinkService,
    private toastr: ToastrService,
    private router: Router 
    ) { }

  ngOnInit() {
    this.loadBacklinks();
  }
  loadBacklinks(){
    this._backlinkService.findAll().subscribe(res => {
      this.backlinks = res;
      console.log(this.backlinks);
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }
}
