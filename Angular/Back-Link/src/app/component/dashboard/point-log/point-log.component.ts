import { Component, OnInit } from '@angular/core';
import { Logs } from 'src/app/model/logs.model';
import { LogService } from 'src/app/service/log.service';


@Component({
  selector: 'app-point-log',
  templateUrl: './point-log.component.html',
  styleUrls: ['./point-log.component.css']
})
export class PointLogComponent implements OnInit {
  
  listLogs: Logs[];
  
  constructor(private _logService: LogService) {}

  ngOnInit() {
    this._logService.findAll().subscribe(data => this.listLogs = data)
  }

}
