import { Gender } from './employee.model'; 
import { Role } from './role.model';
export interface RegisterRequest {
  name: string;
  gender: Gender;
  contactNumber: string;
  address: string;
  email: string;
  password: string;
  roleId: number;
}