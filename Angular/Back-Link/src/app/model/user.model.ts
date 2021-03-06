import { Roles } from './roles.model';

export class User {

    constructor(
      public id?: string,
      public username?: string,
      public email?: string,
      public phone?: string,
      public address?: string,
      public roles?: Roles[],
      public fullname?: string,
      public birthday?: Date,
      public gender?: boolean,
      public updateAt?: Date,      
      public createAt?: Date,
      public password?: string,
      public avatar?: string,
      public point?: number,
      public lockpoint?: number
    ) {}
}
