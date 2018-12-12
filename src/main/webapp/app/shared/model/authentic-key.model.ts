import { IProductDetails } from 'app/shared/model//product-details.model';

export interface IAuthenticKey {
    id?: number;
    uniqueKey?: number;
    productId?: number;
    assignmentStatus?: boolean;
    validStatus?: boolean;
    productDetails?: IProductDetails;
}

export class AuthenticKey implements IAuthenticKey {
    constructor(
        public id?: number,
        public uniqueKey?: number,
        public productId?: number,
        public assignmentStatus?: boolean,
        public validStatus?: boolean,
        public productDetails?: IProductDetails
    ) {
        this.assignmentStatus = this.assignmentStatus || false;
        this.validStatus = this.validStatus || false;
    }
}
