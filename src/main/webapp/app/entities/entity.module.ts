import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationSecretKeyModule } from './secret-key/secret-key.module';
import { JhipsterSampleApplicationProductModule } from './product/product.module';
import { JhipsterSampleApplicationProductDetailsModule } from './product-details/product-details.module';
import { JhipsterSampleApplicationAuthenticKeyModule } from './authentic-key/authentic-key.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterSampleApplicationSecretKeyModule,
        JhipsterSampleApplicationProductModule,
        JhipsterSampleApplicationProductDetailsModule,
        JhipsterSampleApplicationAuthenticKeyModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
