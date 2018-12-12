import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAuthenticKey } from 'app/shared/model/authentic-key.model';
import { Principal } from 'app/core';
import { AuthenticKeyService } from './authentic-key.service';

@Component({
    selector: 'jhi-authentic-key',
    templateUrl: './authentic-key.component.html'
})
export class AuthenticKeyComponent implements OnInit, OnDestroy {
    authenticKeys: IAuthenticKey[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private authenticKeyService: AuthenticKeyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.authenticKeyService.query().subscribe(
            (res: HttpResponse<IAuthenticKey[]>) => {
                this.authenticKeys = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAuthenticKeys();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAuthenticKey) {
        return item.id;
    }

    registerChangeInAuthenticKeys() {
        this.eventSubscriber = this.eventManager.subscribe('authenticKeyListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
