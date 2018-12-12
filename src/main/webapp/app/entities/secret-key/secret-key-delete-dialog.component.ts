import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISecretKey } from 'app/shared/model/secret-key.model';
import { SecretKeyService } from './secret-key.service';

@Component({
    selector: 'jhi-secret-key-delete-dialog',
    templateUrl: './secret-key-delete-dialog.component.html'
})
export class SecretKeyDeleteDialogComponent {
    secretKey: ISecretKey;

    constructor(private secretKeyService: SecretKeyService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.secretKeyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'secretKeyListModification',
                content: 'Deleted an secretKey'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-secret-key-delete-popup',
    template: ''
})
export class SecretKeyDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ secretKey }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SecretKeyDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.secretKey = secretKey;
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
