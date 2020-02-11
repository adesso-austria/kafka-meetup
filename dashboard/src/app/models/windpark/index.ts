import { Serializable } from 'src/app/services/serializable';
import { Turbine } from '../turbine';

interface WindparkInterface {
  id: string;
  turbines: Array<Turbine>;
}

export class Windpark extends Serializable implements WindparkInterface {
  private _turbines = new Array<Turbine>();

  constructor(private _id: string) {
    super(_id);
  }

  get id(): string {
    return this._id;
  }

  get turbines(): Turbine[] {
    return this._turbines;
  }

  set turbines(turbines: Turbine[]) {
    if (turbines.length > 0) {
      this._turbines = turbines;
    }
  }

  addTurbine(turbine: Turbine): void {
    this._turbines.push(turbine);
  }
}
