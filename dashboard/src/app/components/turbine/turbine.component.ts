import { Component, OnInit, Input } from '@angular/core';
import { Turbine, TurbineStatus } from 'src/app/models/turbine';

@Component({
  selector: 'app-turbine',
  templateUrl: './turbine.component.html',
  styleUrls: ['./turbine.component.scss']
})
export class TurbineComponent implements OnInit {
  @Input() turbine: Turbine;

  constructor() { }

  ngOnInit() {
  }


  getTurbineStatus(): string {
    switch (this.turbine.status) {
      case TurbineStatus.CRITICAL:
        return '⛔️';
      case TurbineStatus.OPERATIONAL:
        return '💚';
      case TurbineStatus.DANGEROUS:
        return '❌';
    }
  }

}
