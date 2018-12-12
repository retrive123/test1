import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProductDetails } from 'app/shared/model/product-details.model';
import { Principal } from 'app/core';
import { ProductDetailsService } from './product-details.service';

@Component({
    selector: 'jhi-product-details',
    templateUrl: './product-details.component.html'
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
    productDetails: IProductDetails[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private productDetailsService: ProductDetailsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.productDetailsService.query().subscribe(
            (res: HttpResponse<IProductDetails[]>) => {
                this.productDetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProductDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProductDetails) {
        return item.id;
    }

    registerChangeInProductDetails() {
        this.eventSubscriber = this.eventManager.subscribe('productDetailsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
