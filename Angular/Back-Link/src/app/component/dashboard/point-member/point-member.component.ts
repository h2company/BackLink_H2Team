import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-point-member',
  templateUrl: './point-member.component.html',
  styleUrls: ['./point-member.component.css']
})
export class PointMemberComponent implements OnInit {

  id: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
      this.route.paramMap.subscribe((params : ParamMap) => {
        this.id = params.get('id');
      })
  }

}
