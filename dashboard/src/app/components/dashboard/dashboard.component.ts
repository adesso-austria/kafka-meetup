import { Component, OnInit } from '@angular/core';
import { TurbineService } from 'src/app/services/turbine.service';
import { Observable } from 'rxjs';
import { Windpark } from 'src/app/models/windpark';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  constructor(private turbineS: TurbineService) {}

  ngOnInit() {}

  getWinparks(): Observable<Windpark[]> {
    return this.turbineS.windparks$;
  }
}
