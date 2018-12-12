import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISecretKey } from 'app/shared/model/secret-key.model';

@Component({
    selector: 'jhi-secret-key-detail',
    templateUrl: './secret-key-detail.component.html'
})
export class SecretKeyDetailComponent implements OnInit {
    secretKey: ISecretKey;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ secretKey }) => {
            this.secretKey = secretKey;
        });
    }

    previousState() {
        window.history.back();
    }
}
