export class User {

  constructor(user?: User) {
    if(user) {
      this.id = user.id;
      this.username = user.username;
      this.password = user.password;
      this.name = user.name ;
      this.lastName = user.lastName;
      this.secondSurname = user.secondSurname;
      this.phoneNumber = user.phoneNumber;
      this.center = user.center;
      this.role = user.role; 
      this.gender = user.gender;
    }
  }

  id: number;
  username: string;
  password: string;
  name: string;
  lastName: string;
  secondSurname: string;
  phoneNumber: string;
  center: number;
  gender: string;
  role: {
    id: number;
    name: string;
  }
}
