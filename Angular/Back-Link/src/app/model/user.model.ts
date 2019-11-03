import { Roles } from './roles.model';

export class User {

    constructor(
      public id?: string,
      public username?: string,
      public point? :string,
      public email?: string,
      public phone?: string,
      public address?: string,
      public roles?: Roles[],
      public fullname?: string,
      public birthday?: Date,
      public gender?: boolean,
      public updateAt?: Date,      
      public createAt?: Date,
      public password?: string
    ) {}
}
