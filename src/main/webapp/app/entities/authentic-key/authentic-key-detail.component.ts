import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAuthenticKey } from 'app/shared/model/authentic-key.model';

@Component({
    selector: 'jhi-authentic-key-detail',
    templateUrl: './authentic-key-detail.component.html'
})
export class AuthenticKeyDetailComponent implements OnInit {
    authenticKey: IAuthenticKey;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ authenticKey }) => {
            this.authenticKey = authenticKey;
        });
    }

    previousState() {
        window.history.back();
    }
}
