import { Component, OnInit } from '@angular/core';
import { Action } from 'src/app/model/action.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { requiredValidator, urlValidator } from 'src/app/util/custom-validator';
import { BsLocaleService } from 'ngx-bootstrap/datepicker/ngx-bootstrap-datepicker';
import { ToastrService } from 'ngx-toastr';
import { ActionService } from 'src/app/service/action.service';

@Component({
  selector: 'app-add-action',
  templateUrl: './add-action.component.html',
  styleUrls: ['./add-action.component.css']
})
export class AddActionComponent implements OnInit {

  action: Action = new Action();
  actionFormGroup: FormGroup;
  controls: any;
  tempData: any;
  isSubmit: boolean = false;
  isValidEndDate: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private bsLocaleService: BsLocaleService,
    private toastr: ToastrService,
    private _actionService: ActionService
  ) { }

  ngOnInit() {
    this.createForm();
    this.defaultValue();
  }

  defaultValue() {    
    this.action.username = '';
    this.action.urlAction = '';
    this.action.saveVA = false;
    this.action.blockPixel = false;
    this.action.filterVA = false;
    this.action.beginTime = new Date().getTime();
    this.action.endTime = this.action.beginTime + 24 * 60 * 60 * 1000;

    this.controls = this.actionFormGroup.controls;
    this.tempData = {
      keywords : [],
      userAgent : [],
      searchEngine : []
    }
  }

  createForm() {
    this.actionFormGroup = this.formBuilder.group({
      actionUrl: ['', [requiredValidator(), urlValidator()]],
      point: ['', [requiredValidator()]]
    });
    this.bsLocaleService.use('vi');
  }

  onValueChangeBlockPixel(event){
    this.action.blockPixel = event;
  }

  onValueChangeFilterVA(event){
    this.action.filterVA = event;
  }

  onValueChangeSaveVA(event){
    this.action.saveVA = event;
  }  

  onChangeEndTime(date: Date){
    if(date.getTime() <= new Date().getTime()){
      this.toastr.error('Thông báo!', 'Ngày kết thúc phải lớn hơn ngày bắt đầu',{
        positionClass: 'toast-top-right'
      });
      this.isValidEndDate = false;
      return;
    }
    this.isValidEndDate = true;
    this.action.endTime = date.getTime();
  }

  onSubmit(){
    this.action.keywords = [];
    this.action.userAgent = [];
    this.action.searchEngine = [];

    this.tempData.keywords.forEach(item => {
      this.action.keywords.push(item.value);
    })
    this.tempData.searchEngine.forEach(item => {
      this.action.searchEngine.push(item.value);
    })
    this.tempData.userAgent.forEach(item => {
      this.action.userAgent.push(item.value);
    })

    if (!this.actionFormGroup.valid || !this.checkData() || !this.isValidEndDate) {      
      this.isSubmit = true;
      this.toastr.error('Thông báo!', 'Vui lòng nhập chính xác thông tin!', {
        positionClass: 'toast-top-right'
      });
      return;
    }
    this._actionService.save(this.action).subscribe(data => {
      this.action = data;
      this.resetForm(this.action);
      this.toastr.success('Thông báo!', 'Cập nhật thông tin thành công!', {
        positionClass: 'toast-top-right'
      })
    }, errs => {
      this.toastr.error('Thông báo!', errs.error.message, {
        positionClass: 'toast-top-right'
      });
    });
    this.isSubmit = false;
  }

  resetForm(action: Action) {
    this.defaultValue();
    this.isSubmit = false;
    Object.keys(this.controls).forEach(elm => {
      this.controls[elm].reset(this.action[elm]);
    });
  }

  checkData(){
    if(!this.action.keywords || !this.action.userAgent || !this.action.searchEngine){
      return false;
    }
    return true;
  }

  removeError(event){
    this.isSubmit = false;
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

}
