import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LoginModalService, Principal, Account } from 'app/core';
import { HomeService } from 'app/home/home.service';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    secretKey: any;
    responseData: any;
    isError = false;
    constructor(private principal: Principal, private homeService: HomeService, private loginModalService: LoginModalService, private eventManager: JhiEventManager) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
    checkAuthentic() {
        this.responseData = undefined;
        this.isError = false;
        this.homeService.checkAuthenticity(this.secretKey).subscribe( response => {
           // console.log(response);
            if (response.body) {
                this.responseData = response.body;
                // this.responseData.isValid = true;
            }
        }, error => {
            // console.log(error);
            this.responseData = error;
            this.isError = true;
            // this.responseData.isValid = false;
        });
    }
 }
