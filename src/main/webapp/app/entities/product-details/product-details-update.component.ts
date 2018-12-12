import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IProductDetails } from 'app/shared/model/product-details.model';
import { ProductDetailsService } from './product-details.service';

@Component({
    selector: 'jhi-product-details-update',
    templateUrl: './product-details-update.component.html'
})
export class ProductDetailsUpdateComponent implements OnInit {
    productDetails: IProductDetails;
    isSaving: boolean;
    productManuDateDp: any;

    constructor(private productDetailsService: ProductDetailsService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ productDetails }) => {
            this.productDetails = productDetails;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.productDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.productDetailsService.update(this.productDetails));
        } else {
            this.subscribeToSaveResponse(this.productDetailsService.create(this.productDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProductDetails>>) {
        result.subscribe((res: HttpResponse<IProductDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
