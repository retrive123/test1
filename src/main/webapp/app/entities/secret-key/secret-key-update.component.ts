import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISecretKey } from 'app/shared/model/secret-key.model';
import { SecretKeyService } from './secret-key.service';

@Component({
    selector: 'jhi-secret-key-update',
    templateUrl: './secret-key-update.component.html'
})
export class SecretKeyUpdateComponent implements OnInit {
    secretKey: ISecretKey;
    isSaving: boolean;

    constructor(private secretKeyService: SecretKeyService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ secretKey }) => {
            this.secretKey = secretKey;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.secretKey.id !== undefined) {
            this.subscribeToSaveResponse(this.secretKeyService.update(this.secretKey));
        } else {
            this.subscribeToSaveResponse(this.secretKeyService.create(this.secretKey));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISecretKey>>) {
        result.subscribe((res: HttpResponse<ISecretKey>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
