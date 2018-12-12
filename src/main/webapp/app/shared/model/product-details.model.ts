import { Moment } from 'moment';

export interface IProductDetails {
    id?: number;
    productName?: string;
    manuId?: number;
    manuName?: string;
    productId?: number;
    productManuDate?: Moment;
}

export class ProductDetails implements IProductDetails {
    constructor(
        public id?: number,
        public productName?: string,
        public manuId?: number,
        public manuName?: string,
        public productId?: number,
        public productManuDate?: Moment
    ) {}
}
