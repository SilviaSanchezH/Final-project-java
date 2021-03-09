import { User } from "./user";

export class Worker extends User {

  constructor(worker?: Worker) {
    super(worker);
    if(worker) {
      this.occupation = worker.occupation;
      this.professionalNumber = worker.professionalNumber;
    }
  }

  occupation: string;
  professionalNumber: string;
}
