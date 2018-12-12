/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AuthenticKeyComponent } from 'app/entities/authentic-key/authentic-key.component';
import { AuthenticKeyService } from 'app/entities/authentic-key/authentic-key.service';
import { AuthenticKey } from 'app/shared/model/authentic-key.model';

describe('Component Tests', () => {
    describe('AuthenticKey Management Component', () => {
        let comp: AuthenticKeyComponent;
        let fixture: ComponentFixture<AuthenticKeyComponent>;
        let service: AuthenticKeyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AuthenticKeyComponent],
                providers: []
            })
                .overrideTemplate(AuthenticKeyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AuthenticKeyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AuthenticKeyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AuthenticKey(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.authenticKeys[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
