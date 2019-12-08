import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-action',
  templateUrl: './add-action.component.html',
  styleUrls: ['./add-action.component.css']
})
export class AddActionComponent implements OnInit {

  items = [];

  constructor() { }

  ngOnInit() {
    this.items = ['Pizza', 'Pasta', 'Parmesan']
  }

  onClick(){
    console.log(123)
  }

}
