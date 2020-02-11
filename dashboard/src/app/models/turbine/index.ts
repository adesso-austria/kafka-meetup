import { Serializable } from 'src/app/services/serializable';

export enum TurbineStatus {
  OPERATIONAL = 'OPERATIONAL',
  CRITICAL = 'CRITICAL',
  DANGEROUS = 'DANGEROUS'
}

interface TurbineInterface {
  id: string;
  windparkId: string;
  tempOutside: number;
  tempInside: number;
  bats: boolean;
  windSpeed: number;
  status: TurbineStatus;
}

export class Turbine extends Serializable implements TurbineInterface {
  constructor(
    private _windparkId: string,
    private _id: string,
    private _tempOutside: number,
    private _tempInside: number,
    private _bats: boolean,
    private _windSpeed: number,
    private _status: TurbineStatus
  ) {
    super({
      _windparkId,
      _id,
      _tempOutside,
      _tempInside,
      _bats,
      _windSpeed,
      _status
    });
  }

  get id(): string {
    return this._id;
  }

  get tempOutside(): number {
    return this._tempOutside;
  }

  set tempOutside(tempOutside: number) {
    this._tempOutside = tempOutside;
  }

  get bats(): boolean {
    return this._bats;
  }

  set bats(bats: boolean) {
    this._bats = bats;
  }

  get tempInside(): number {
    return this._tempInside;
  }

  set tempInside(tempInside: number) {
    this._tempInside = tempInside;
  }

  get windSpeed(): number {
    return this._windSpeed;
  }

  set windSpeed(windSpeed: number) {
    this._windSpeed = windSpeed;
  }

  get status(): TurbineStatus {
    return this._status;
  }

  set status(status: TurbineStatus) {
    this._status = status;
  }

  get windparkId(): string {
    return this._windparkId;
  }

  set windparkId(windparkId: string) {
    this._windparkId = windparkId;
  }
}
