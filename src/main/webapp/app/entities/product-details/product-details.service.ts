import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProductDetails } from 'app/shared/model/product-details.model';

type EntityResponseType = HttpResponse<IProductDetails>;
type EntityArrayResponseType = HttpResponse<IProductDetails[]>;

@Injectable({ providedIn: 'root' })
export class ProductDetailsService {
    public resourceUrl = SERVER_API_URL + 'api/product-details';

    constructor(private http: HttpClient) {}

    create(productDetails: IProductDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(productDetails);
        return this.http
            .post<IProductDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(productDetails: IProductDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(productDetails);
        return this.http
            .put<IProductDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProductDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProductDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(productDetails: IProductDetails): IProductDetails {
        const copy: IProductDetails = Object.assign({}, productDetails, {
            productManuDate:
                productDetails.productManuDate != null && productDetails.productManuDate.isValid()
                    ? productDetails.productManuDate.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.productManuDate = res.body.productManuDate != null ? moment(res.body.productManuDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((productDetails: IProductDetails) => {
                productDetails.productManuDate = productDetails.productManuDate != null ? moment(productDetails.productManuDate) : null;
            });
        }
        return res;
    }
}
