import {Role} from './role.model';

export enum Gender{

  Male = 'Male',
  Female = 'Female',
  Other = 'Other'

}

export interface Employee {
    employeeId: number;
    name: string;
    gender: Gender;
    contactNumber: string;
    address: string;
    email: string;
    password: string;
    role: Role;
  }