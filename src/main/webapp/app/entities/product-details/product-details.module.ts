import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ProductDetailsComponent,
    ProductDetailsDetailComponent,
    ProductDetailsUpdateComponent,
    ProductDetailsDeletePopupComponent,
    ProductDetailsDeleteDialogComponent,
    productDetailsRoute,
    productDetailsPopupRoute
} from './';

const ENTITY_STATES = [...productDetailsRoute, ...productDetailsPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProductDetailsComponent,
        ProductDetailsDetailComponent,
        ProductDetailsUpdateComponent,
        ProductDetailsDeleteDialogComponent,
        ProductDetailsDeletePopupComponent
    ],
    entryComponents: [
        ProductDetailsComponent,
        ProductDetailsUpdateComponent,
        ProductDetailsDeleteDialogComponent,
        ProductDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationProductDetailsModule {}
