import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductDetails } from 'app/shared/model/product-details.model';
import { ProductDetailsService } from './product-details.service';
import { ProductDetailsComponent } from './product-details.component';
import { ProductDetailsDetailComponent } from './product-details-detail.component';
import { ProductDetailsUpdateComponent } from './product-details-update.component';
import { ProductDetailsDeletePopupComponent } from './product-details-delete-dialog.component';
import { IProductDetails } from 'app/shared/model/product-details.model';

@Injectable({ providedIn: 'root' })
export class ProductDetailsResolve implements Resolve<IProductDetails> {
    constructor(private service: ProductDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProductDetails> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProductDetails>) => response.ok),
                map((productDetails: HttpResponse<ProductDetails>) => productDetails.body)
            );
        }
        return of(new ProductDetails());
    }
}

export const productDetailsRoute: Routes = [
    {
        path: 'product-details',
        component: ProductDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-details/:id/view',
        component: ProductDetailsDetailComponent,
        resolve: {
            productDetails: ProductDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-details/new',
        component: ProductDetailsUpdateComponent,
        resolve: {
            productDetails: ProductDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-details/:id/edit',
        component: ProductDetailsUpdateComponent,
        resolve: {
            productDetails: ProductDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductDetails'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productDetailsPopupRoute: Routes = [
    {
        path: 'product-details/:id/delete',
        component: ProductDetailsDeletePopupComponent,
        resolve: {
            productDetails: ProductDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
