import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { requiredValidator, emailValidator } from 'src/app/util/custom-validator';

@Component({
  selector: 'app-add-backlink',
  templateUrl: './add-backlink.component.html',
  styleUrls: ['./add-backlink.component.css']
})
export class AddBacklinkComponent implements OnInit {
  userFormGroup: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.createForm();
  }
  createForm() {
      this.userFormGroup = this.formBuilder.group({
        backlink_url: ['', [requiredValidator()]],
        searchEngine: ['', [requiredValidator()]],
        keywords: ['', [requiredValidator()]],
        userAgent: ['', [requiredValidator()]],
        point: ['', [requiredValidator()]],
        beginTime: ['', [requiredValidator()]],
        endTime: ['', [requiredValidator()]],
        backlink_code: ['', [requiredValidator()]],
        filterVA: ['', [requiredValidator()]],
        saveVA: ['', [requiredValidator()]]
      });
    }
}
