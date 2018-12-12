import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    SecretKeyComponent,
    SecretKeyDetailComponent,
    SecretKeyUpdateComponent,
    SecretKeyDeletePopupComponent,
    SecretKeyDeleteDialogComponent,
    secretKeyRoute,
    secretKeyPopupRoute
} from './';

const ENTITY_STATES = [...secretKeyRoute, ...secretKeyPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SecretKeyComponent,
        SecretKeyDetailComponent,
        SecretKeyUpdateComponent,
        SecretKeyDeleteDialogComponent,
        SecretKeyDeletePopupComponent
    ],
    entryComponents: [SecretKeyComponent, SecretKeyUpdateComponent, SecretKeyDeleteDialogComponent, SecretKeyDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationSecretKeyModule {}
