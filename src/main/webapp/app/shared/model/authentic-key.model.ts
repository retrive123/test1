import { Moment } from 'moment';
import { IProductDetails } from 'app/shared/model//product-details.model';

export interface IAuthenticKey {
    id?: number;
    uniqueKey?: string;
    assignmentStatus?: boolean;
    validStatus?: boolean;
    validationdate?: Moment;
    productDetails?: IProductDetails;
}

export class AuthenticKey implements IAuthenticKey {
    constructor(
        public id?: number,
        public uniqueKey?: string,
        public assignmentStatus?: boolean,
        public validStatus?: boolean,
        public validationdate?: Moment,
        public productDetails?: IProductDetails
    ) {
        this.assignmentStatus = this.assignmentStatus || false;
        this.validStatus = this.validStatus || false;
    }
}
