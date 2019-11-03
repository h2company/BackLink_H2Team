import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { requiredValidator, emailValidator, urlValidator, minmaxValidator } from 'src/app/util/custom-validator';
import { Backlink } from 'src/app/model/backlink.model';
import { BacklinkService } from 'src/app/service/backlink.service';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { viLocale } from 'ngx-bootstrap/locale';

@Component({
  selector: 'app-add-backlink',
  templateUrl: './add-backlink.component.html',
  styleUrls: ['./add-backlink.component.css']
})
export class AddBacklinkComponent implements OnInit {
  backlink: Backlink = new Backlink();
  userFormGroup: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private _backlinkService: BacklinkService,
    private toastr: ToastrService,
    private router: Router,
    private bsLocaleService: BsLocaleService
  ) { }

  ngOnInit() {
    this.createForm();
  }
  createForm() {
    this.userFormGroup = this.formBuilder.group({
      backlink_url: ['', [requiredValidator(), urlValidator()]],
      point: ['', [requiredValidator(), minmaxValidator(0,9999)]],
      beginTime: ['', []],
      endTime: ['', []],
      filterVA: [false, []],
      saveVA: [false, []]
    });
    this.bsLocaleService.use('vi');
  }
  onSubmit(){
    console.log(this.userFormGroup.controls);
    if(this.userFormGroup.invalid){
      this.toastr.error('Thông báo!', 'Vui lòng nhập chính xác thông tin!',{
        positionClass: 'toast-top-right'
      });
      return;
    }
    const controls = this.userFormGroup.controls;
    this.backlink.urlBacklink = controls.backlink_url.value;
    this.backlink.point = controls.point.value;
    this.backlink.filterVA = controls.filterVA.value;
    this.backlink.saveVA = controls.saveVA.value;
    this.backlink.beginTime = new Date(controls.beginTime.value).getTime();
    this.backlink.endTime = new Date(controls.endTime.value).getTime();

    this._backlinkService.save(this.backlink).subscribe(res => {
      this.toastr.success('Thông báo!', 'Thao tác thành công !', {
        positionClass: 'toast-top-right'
      });
      this.router.navigate(['/statistical-access']);
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }
}
