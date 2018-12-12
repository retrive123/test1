import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAuthenticKey } from 'app/shared/model/authentic-key.model';
import { AuthenticKeyService } from './authentic-key.service';
import { IProductDetails } from 'app/shared/model/product-details.model';
import { ProductDetailsService } from 'app/entities/product-details';

@Component({
    selector: 'jhi-authentic-key-update',
    templateUrl: './authentic-key-update.component.html'
})
export class AuthenticKeyUpdateComponent implements OnInit {
    authenticKey: IAuthenticKey;
    isSaving: boolean;

    productdetails: IProductDetails[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private authenticKeyService: AuthenticKeyService,
        private productDetailsService: ProductDetailsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ authenticKey }) => {
            this.authenticKey = authenticKey;
        });
        this.productDetailsService.query({ filter: 'authentickey-is-null' }).subscribe(
            (res: HttpResponse<IProductDetails[]>) => {
                if (!this.authenticKey.productDetails || !this.authenticKey.productDetails.id) {
                    this.productdetails = res.body;
                } else {
                    this.productDetailsService.find(this.authenticKey.productDetails.id).subscribe(
                        (subRes: HttpResponse<IProductDetails>) => {
                            this.productdetails = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.authenticKey.id !== undefined) {
            this.subscribeToSaveResponse(this.authenticKeyService.update(this.authenticKey));
        } else {
            this.subscribeToSaveResponse(this.authenticKeyService.create(this.authenticKey));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAuthenticKey>>) {
        result.subscribe((res: HttpResponse<IAuthenticKey>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProductDetailsById(index: number, item: IProductDetails) {
        return item.id;
    }
}
