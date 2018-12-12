import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISecretKey } from 'app/shared/model/secret-key.model';
import { Principal } from 'app/core';
import { SecretKeyService } from './secret-key.service';

@Component({
    selector: 'jhi-secret-key',
    templateUrl: './secret-key.component.html'
})
export class SecretKeyComponent implements OnInit, OnDestroy {
    secretKeys: ISecretKey[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private secretKeyService: SecretKeyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.secretKeyService.query().subscribe(
            (res: HttpResponse<ISecretKey[]>) => {
                this.secretKeys = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSecretKeys();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISecretKey) {
        return item.id;
    }

    registerChangeInSecretKeys() {
        this.eventSubscriber = this.eventManager.subscribe('secretKeyListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
