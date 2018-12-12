import { ISecretKey } from 'app/shared/model//secret-key.model';

export interface IProduct {
    id?: number;
    productName?: string;
    manuId?: number;
    manuName?: string;
    productId?: number;
    secretKey?: ISecretKey;
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public productName?: string,
        public manuId?: number,
        public manuName?: string,
        public productId?: number,
        public secretKey?: ISecretKey
    ) {}
}
