export class Roles {
  constructor(
    public id: number,
    public name: RoleName
  ) {}
}

export enum RoleName {
  ROLE_CUSTOMER = "ROLE_CUSTOMER",
  ROLE_MANAGER = "ROLE_MANAGER", 
  ROLE_ADMIN = "ROLE_ADMIN"
}

export const roles = [
  {id: 0, name: "Thành Viên"},
  {id: 1, name: "Admin"}
];
