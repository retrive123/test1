import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductDetails } from 'app/shared/model/product-details.model';
import { ProductDetailsService } from './product-details.service';

@Component({
    selector: 'jhi-product-details-delete-dialog',
    templateUrl: './product-details-delete-dialog.component.html'
})
export class ProductDetailsDeleteDialogComponent {
    productDetails: IProductDetails;

    constructor(
        private productDetailsService: ProductDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.productDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'productDetailsListModification',
                content: 'Deleted an productDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-product-details-delete-popup',
    template: ''
})
export class ProductDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ productDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProductDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.productDetails = productDetails;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
