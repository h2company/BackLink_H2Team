export class Roles {
  constructor(
    public id: number,
    public name: RoleName
  ) {}
}

export enum RoleName {
  ROLE_CUSTOMER, ROLE_MANAGER, ROLE_ADMIN
}

export const roles = [
  {id: 1, name: "Thành Viên"},
  {id: 2, name: "Admin"}
];
