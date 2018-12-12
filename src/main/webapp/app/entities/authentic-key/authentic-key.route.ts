import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AuthenticKey } from 'app/shared/model/authentic-key.model';
import { AuthenticKeyService } from './authentic-key.service';
import { AuthenticKeyComponent } from './authentic-key.component';
import { AuthenticKeyDetailComponent } from './authentic-key-detail.component';
import { AuthenticKeyUpdateComponent } from './authentic-key-update.component';
import { AuthenticKeyDeletePopupComponent } from './authentic-key-delete-dialog.component';
import { IAuthenticKey } from 'app/shared/model/authentic-key.model';

@Injectable({ providedIn: 'root' })
export class AuthenticKeyResolve implements Resolve<IAuthenticKey> {
    constructor(private service: AuthenticKeyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AuthenticKey> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AuthenticKey>) => response.ok),
                map((authenticKey: HttpResponse<AuthenticKey>) => authenticKey.body)
            );
        }
        return of(new AuthenticKey());
    }
}

export const authenticKeyRoute: Routes = [
    {
        path: 'authentic-key',
        component: AuthenticKeyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AuthenticKeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'authentic-key/:id/view',
        component: AuthenticKeyDetailComponent,
        resolve: {
            authenticKey: AuthenticKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AuthenticKeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'authentic-key/new',
        component: AuthenticKeyUpdateComponent,
        resolve: {
            authenticKey: AuthenticKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AuthenticKeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'authentic-key/:id/edit',
        component: AuthenticKeyUpdateComponent,
        resolve: {
            authenticKey: AuthenticKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AuthenticKeys'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const authenticKeyPopupRoute: Routes = [
    {
        path: 'authentic-key/:id/delete',
        component: AuthenticKeyDeletePopupComponent,
        resolve: {
            authenticKey: AuthenticKeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AuthenticKeys'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
