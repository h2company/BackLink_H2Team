import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { PointMember } from 'src/app/model/point-member.model';
import { PointMemberService } from 'src/app/service/point-member.service';

@Component({
  selector: 'app-point-member',
  templateUrl: './point-member.component.html',
  styleUrls: ['./point-member.component.css']
})
export class PointMemberComponent implements OnInit {

  listPointMember: PointMember[];

  constructor(private _pointmemberService: PointMemberService){}
  
  ngOnInit() {
     this._pointmemberService.findAll().subscribe(data => this.listPointMember = data);
  }

}
