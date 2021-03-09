export class User {

  constructor(user?: User) {
    if(user) {
      this.id = user.id;
      this.username = user.username;
      this.password = user.password;
      this.name = user.name ;
      this.lastname = user.lastname;
      this.phoneNumber = user.phoneNumber;
      this.center = user.center;
      this.role = user.role; 
    }
  }

  id: number;
  username: string;
  password: string;
  name: string;
  lastname: string;
  phoneNumber: string;
  center: number;
  role: {
    id: number;
    name: string;
  }
}
