import { TurbineStatus } from './../turbine/index';
interface Id {
  windparkID: string;
  turbineID: string;
}

export interface APIResponse {
  tempOutside: number;
  tempInside: number;
  bats: boolean;
  windSpeed: number;
  timestamp: string;
  status: TurbineStatus;
  id: Id;
}
