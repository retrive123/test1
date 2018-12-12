import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './';
import { HomeService } from 'app/home/home.service';

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild([HOME_ROUTE])],
    declarations: [HomeComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [HomeService]
})
export class JhipsterSampleApplicationHomeModule {}
