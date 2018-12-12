import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SecretKey } from 'app/shared/model/secret-key.model';
import { SecretKeyService } from './secret-key.service';
import { SecretKeyComponent } from './secret-key.component';
import { SecretKeyDetailComponent } from './secret-key-detail.component';
import { SecretKeyUpdateComponent } from './secret-key-update.component';
import { SecretKeyDeletePopupComponent } from './secret-key-delete-dialog.component';
import { ISecretKey } from 'app/shared/model/secret-key.model';

@Injectable({ providedIn: 'root' })
export class SecretKeyResolve implements Resolve<ISecretKey> {
    constructor(private service: SecretKeyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SecretKey> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SecretKey>) => response.ok),
                map((secretKey: HttpResponse<SecretKey>) => secretKey.body)
            );
        }
        return of(new SecretKey());
    }
}

export const secretKeyRoute: Routes = [
    {
        path: 'secret-key',
        component: SecretKeyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SecretKeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'secret-key/:id/view',
        component: SecretKeyDetailComponent,
        resolve: {
            secretKey: SecretKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SecretKeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'secret-key/new',
        component: SecretKeyUpdateComponent,
        resolve: {
            secretKey: SecretKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SecretKeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'secret-key/:id/edit',
        component: SecretKeyUpdateComponent,
        resolve: {
            secretKey: SecretKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SecretKeys'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const secretKeyPopupRoute: Routes = [
    {
        path: 'secret-key/:id/delete',
        component: SecretKeyDeletePopupComponent,
        resolve: {
            secretKey: SecretKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SecretKeys'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
