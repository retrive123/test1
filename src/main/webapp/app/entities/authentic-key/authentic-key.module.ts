import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    AuthenticKeyComponent,
    AuthenticKeyDetailComponent,
    AuthenticKeyUpdateComponent,
    AuthenticKeyDeletePopupComponent,
    AuthenticKeyDeleteDialogComponent,
    authenticKeyRoute,
    authenticKeyPopupRoute
} from './';

const ENTITY_STATES = [...authenticKeyRoute, ...authenticKeyPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AuthenticKeyComponent,
        AuthenticKeyDetailComponent,
        AuthenticKeyUpdateComponent,
        AuthenticKeyDeleteDialogComponent,
        AuthenticKeyDeletePopupComponent
    ],
    entryComponents: [
        AuthenticKeyComponent,
        AuthenticKeyUpdateComponent,
        AuthenticKeyDeleteDialogComponent,
        AuthenticKeyDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAuthenticKeyModule {}
