import {Employee} from './employee.model';
import {Asset} from './asset.model';
import { IssueType} from './issue-type.model';

export enum Status {
    Pending = 'Pending',
    Transit = 'Transit',
    Completed = 'Completed'
}

export interface ServiceRequest {
  serviceRequestId: number;
  employee: Employee;
  asset: Asset;
  description: string;
  issueType: IssueType;
  status: Status;
  requestedAt: string;
}