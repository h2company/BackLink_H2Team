import { Component, OnInit, ViewChild } from '@angular/core';
import { BacklinkService } from 'src/app/service/backlink.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal/ngx-bootstrap-modal';
import { Backlink } from 'src/app/model/backlink.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { requiredValidator, urlValidator } from 'src/app/util/custom-validator';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  query,
  stagger
} from '@angular/animations';

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
  varifyForm: FormGroup;
  backlink: Backlink = new Backlink();
  backlinks = null;
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

  constructor(
    private formBuilder: FormBuilder,
    private _backlinkService: BacklinkService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.createForm();
    this.loadBacklinks();
    window.onmessage = (e) => {
        if(e.data.events) {
          if(e.data.events[0].eventAction[0].event == 'verify') {
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

  onLoadNextPage(){
    this.currentPage++;
    this.loadBacklinks();
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

          console.log($(window).eq(0));
          
          $(window).eq(0).setData = function(param) {
              console.log(param);
          }
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
}