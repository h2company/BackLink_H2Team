export class Roles {
  constructor(
    public id: number,
    public name: RoleName
  ) {}
}

export enum RoleName {
  ROLE_CUSTOMER, ROLE_MANAGER, ROLE_ADMIN
}
