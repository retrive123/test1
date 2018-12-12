import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { ISecretKey } from 'app/shared/model/secret-key.model';
import { SecretKeyService } from 'app/entities/secret-key';

@Component({
    selector: 'jhi-product-update',
    templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
    product: IProduct;
    isSaving: boolean;

    secretkeys: ISecretKey[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private productService: ProductService,
        private secretKeyService: SecretKeyService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ product }) => {
            this.product = product;
        });
        this.secretKeyService.query({ filter: 'product-is-null' }).subscribe(
            (res: HttpResponse<ISecretKey[]>) => {
                if (!this.product.secretKey || !this.product.secretKey.id) {
                    this.secretkeys = res.body;
                } else {
                    this.secretKeyService.find(this.product.secretKey.id).subscribe(
                        (subRes: HttpResponse<ISecretKey>) => {
                            this.secretkeys = [subRes.body].concat(res.body);
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
        if (this.product.id !== undefined) {
            this.subscribeToSaveResponse(this.productService.update(this.product));
        } else {
            this.subscribeToSaveResponse(this.productService.create(this.product));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>) {
        result.subscribe((res: HttpResponse<IProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSecretKeyById(index: number, item: ISecretKey) {
        return item.id;
    }
}
