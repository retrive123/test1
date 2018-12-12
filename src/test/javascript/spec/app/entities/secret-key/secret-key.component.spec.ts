/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SecretKeyComponent } from 'app/entities/secret-key/secret-key.component';
import { SecretKeyService } from 'app/entities/secret-key/secret-key.service';
import { SecretKey } from 'app/shared/model/secret-key.model';

describe('Component Tests', () => {
    describe('SecretKey Management Component', () => {
        let comp: SecretKeyComponent;
        let fixture: ComponentFixture<SecretKeyComponent>;
        let service: SecretKeyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [SecretKeyComponent],
                providers: []
            })
                .overrideTemplate(SecretKeyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SecretKeyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SecretKeyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SecretKey(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.secretKeys[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
