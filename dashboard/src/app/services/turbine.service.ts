import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Windpark } from '../models/windpark';
import { Turbine, TurbineStatus } from '../models/turbine';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { take, filter, map } from 'rxjs/operators';
import { APIResponse } from '../models/api-response';

const API = '/turbines';

const groupBy = (data, keyFn) => data.reduce((agg, item) => {
  const group = keyFn(item);
  agg[group] = [...(agg[group] || []), item];
  return agg;
}, {});

@Injectable({
  providedIn: 'root'
})
export class TurbineService {
  private _windparks = new Array<Windpark>();

  constructor(private http: HttpClient) {
    this.getAllTurbines();
  }

  get windparks$(): Observable<Windpark[]> {
    return of(this._windparks);
  }


  public getAllTurbines(): void {
    this.http.get<APIResponse[]>(API)
    .pipe(
      filter(data => !!data),
      map(data => groupBy(data, item => item.id.windparkID)
    ))
    .subscribe(res => {
      console.log(res);
      Object.keys(res).forEach(key => {
        const wp = new Windpark(key);
        const turbines = res[key] as APIResponse[];

        turbines.map((turbine: APIResponse) => {
          wp.addTurbine(new Turbine(
            turbine.id.windparkID,
            turbine.id.turbineID,
            turbine.tempOutside,
            turbine.tempInside,
            turbine.bats,
            turbine.windSpeed,
            turbine.status
          ));
        });

        this._windparks.push(wp);
      });
    }, err => console.error);
  }
}
