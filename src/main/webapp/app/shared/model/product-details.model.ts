import { Moment } from 'moment';

export interface IProductDetails {
    id?: number;
    productName?: string;
    manuId?: number;
    manuName?: string;
    productId?: string;
    productManuDate?: Moment;
}

export class ProductDetails implements IProductDetails {
    constructor(
        public id?: number,
        public productName?: string,
        public manuId?: number,
        public manuName?: string,
        public productId?: string,
        public productManuDate?: Moment
    ) {}
}
