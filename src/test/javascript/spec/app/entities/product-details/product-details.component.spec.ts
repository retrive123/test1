/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProductDetailsComponent } from 'app/entities/product-details/product-details.component';
import { ProductDetailsService } from 'app/entities/product-details/product-details.service';
import { ProductDetails } from 'app/shared/model/product-details.model';

describe('Component Tests', () => {
    describe('ProductDetails Management Component', () => {
        let comp: ProductDetailsComponent;
        let fixture: ComponentFixture<ProductDetailsComponent>;
        let service: ProductDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProductDetailsComponent],
                providers: []
            })
                .overrideTemplate(ProductDetailsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductDetailsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductDetailsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProductDetails(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.productDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
