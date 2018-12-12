import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuthenticKey } from 'app/shared/model/authentic-key.model';
import { AuthenticKeyService } from './authentic-key.service';

@Component({
    selector: 'jhi-authentic-key-delete-dialog',
    templateUrl: './authentic-key-delete-dialog.component.html'
})
export class AuthenticKeyDeleteDialogComponent {
    authenticKey: IAuthenticKey;

    constructor(
        private authenticKeyService: AuthenticKeyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.authenticKeyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'authenticKeyListModification',
                content: 'Deleted an authenticKey'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-authentic-key-delete-popup',
    template: ''
})
export class AuthenticKeyDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ authenticKey }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AuthenticKeyDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.authenticKey = authenticKey;
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
