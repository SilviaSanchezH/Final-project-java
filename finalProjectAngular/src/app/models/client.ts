import { User } from "./user";

export class Client extends User{

  constructor(client?: Client) {
    super(client);
    if(client) {
      this.address = client.address;
      this.city = client.city;
    }
  }

  address: string;
  city: string;
}
