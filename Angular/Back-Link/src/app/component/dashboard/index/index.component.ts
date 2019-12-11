import { Component, OnInit, ViewChild } from '@angular/core';
import { BacklinkService } from 'src/app/service/backlink.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal/ngx-bootstrap-modal';
import { Backlink } from 'src/app/model/backlink.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { requiredValidator, urlValidator } from 'src/app/util/custom-validator';
import { Action } from 'src/app/model/action.model';
import * as CanvasJS from 'src/assets/canvasjs.min';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  query,
  stagger
} from '@angular/animations';
import { ActionService } from 'src/app/service/action.service';

declare var jQuery: any;

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  animations: [
    trigger('listStagger', [
      transition('* <=> *', [
        query(
          ':enter',
          [
            style({ opacity: 0, transform: 'translateY(-15px)' }),
            stagger(
              '50ms',
              animate(
                '550ms ease-out',
                style({ opacity: 1, transform: 'translateY(0px)' })
              )
            )
          ],
          { optional: true }
        ),
        query(':leave', animate('50ms', style({ opacity: 0 })), {
          optional: true
        })
      ])
    ])
  ]
})
export class IndexComponent implements OnInit {

  @ViewChild('childModal', { static: false }) childModal: ModalDirective;
  @ViewChild('childModalAction', { static: false }) childModalAction: ModalDirective;
  varifyForm: FormGroup;
  backlink: Backlink = new Backlink();
  backlinks = [];

  user_backlink = "";
  user_backlink_info = new Backlink();
  user_backlinks = [];

  actions = [];
  action: Action = new Action();

  blcode = `<a href="" data-backlink="" target="_blank">Nội dung backlink</a>`;
  verify: string = `<script>
    (function(b,d,w,i,f,r){
        b._blSettings={siteId:'22',action: 'verify'};
        f=d.getElementsByTagName('head')[0];
        r=d.createElement('script');
        r.async=1;
        r.src=w+b._blSettings.action+i+b._blSettings.siteId;
        f.appendChild(r);
    })(window,document,'http://localhost:8082/frame/c/','.js?siteId=');
  </script>`;
  math = Math;
  currentPage = 0;
  currentPageAction = 0;

  constructor(
    private formBuilder: FormBuilder,
    private _backlinkService: BacklinkService,
    private _actionService: ActionService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.createForm();
    this.loadBacklinks();
    this.loadCurentBacklinks();
    this.loadActions();
    this.loadChart();
    window.onmessage = (e) => {
        if(e.data.events) {
          if(e.data.events[0].eventAction[0].event == 'verify') {
            e.data.backlink_id = this.backlink.id;
            this._backlinkService.verify(e.data).subscribe(res => {
                this.toastr.success('Thông báo!', res.message, {
                  positionClass: 'toast-top-right'
                });
                this.childModal.toggle();
                this.loadBacklinks();
            }, error => {
              this.toastr.error(error.error.error, error.error.message, {
                positionClass: 'toast-top-right'
              });
            });
          }
        }
    };
  }

  loadChart(){
    setTimeout(function(){
      let chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        exportEnabled: true,
        title: {
          text: "Backlink Tracking"
        },
        data: [{
          type: "column",
          dataPoints: [
            { y: 71, label: "Tháng 1" },
            { y: 55, label: "Tháng 2" },
            { y: 50, label: "Tháng 3" },
            { y: 65, label: "Tháng 4" },
            { y: 95, label: "Tháng 5" },
            { y: 68, label: "Tháng 6" },
            { y: 28, label: "Tháng 7" },
            { y: 34, label: "Tháng 8" },
            { y: 14, label: "Tháng 9" }
          ]
        }]
      });
      chart.render();
    }, 2000)    
  }
  
  createForm() {
    this.varifyForm = this.formBuilder.group({
      urlVerify: ['', [requiredValidator(), urlValidator()]]
    });
  }

  onVerifySubmit() {
    if (this.varifyForm.invalid) {
      this.toastr.error('Thông báo!', 'Vui lòng nhập chính xác thông tin!', {
        positionClass: 'toast-top-right'
      });
      return;
    }
    const controls = this.varifyForm.controls;
    this._backlinkService.checkVerify({
      'id': this.backlink.id,
      'urlAgent': controls.urlVerify.value
    }).subscribe(res => {
      var win = window.open(controls.urlVerify.value, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=400,width=700,height=400");
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
    
  }

  loadCurentBacklinks() {
    this._backlinkService.findAllByUser().subscribe(res => {
      this.user_backlinks = res;
      this.user_backlink = this.user_backlinks[0].id;
      this.onBacklinkChange();
      console.log(this.user_backlink);
    }, error => {
      console.log(error)
    });
  }

  loadBacklinks() {
    this._backlinkService.findAll(this.currentPage).subscribe(res => {
      if(res.length == 0){
        this.currentPage--;
        return;
      }
      if(this.backlinks){
        this.backlinks = [].concat(this.backlinks, res);
      }else{      
        this.backlinks = res;
      }
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }

  loadActions() {
    this._actionService.findAllDashboard(this.currentPageAction).subscribe(res => {
      if(res.length == 0){
        this.currentPageAction--;
        return;
      }
      if(this.actions){
        this.actions = [].concat(this.actions, res);
      }else{      
        this.actions = res;
      }
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }

  onLoadNextPage(){
    this.currentPage++;
    this.loadBacklinks();
  }

  onLoadNextPageAction(){
    this.currentPageAction++;
    this.loadActions();
  }

  onClickBacklinkDetail(id) {
    this._backlinkService.findById(id).subscribe(res => {
      this.backlink = res;
      if (this.backlink) {
        let _this = this;
        (function ($) {
          //Set Backlink
          $('.token.punctuation').eq(2).html("\"" + _this.backlink.urlBacklink);
          $('.token.punctuation').eq(5).html("\"" + _this.backlink.id);
          //Set Verify Code
          $('.token.string').eq(0).html('\''+_this.backlink.id+'\'');
        })(jQuery);
      }
      setTimeout(() => {
        this.childModal.show();
      }, 500);
    }, error => {
      this.toastr.error("Lỗi", "", {
        positionClass: 'toast-top-right'
      });
    });
  }

  onClickActionDetail(id) {
    console.log(id)
    this._actionService.findById(id).subscribe(res => {
      this.action = res;
      if (this.action) {
        let _this = this;
      }
      setTimeout(() => {
        this.childModalAction.show();
      }, 500);
    }, error => {
      this.toastr.error("Lỗi", "", {
        positionClass: 'toast-top-right'
      });
    });
  }

  onBacklinkChange() {
    this._backlinkService.findById(this.user_backlink).subscribe(res => {
      this.user_backlink_info = res;
      console.log(this.user_backlink_info);
      if (this.user_backlink_info) {
        let _this = this;
        (function ($) {
          
        })(jQuery);
      }
    }, error => {
      this.toastr.error(error.error.error, error.error.message, {
        positionClass: 'toast-top-right'
      });
    });
  }
}