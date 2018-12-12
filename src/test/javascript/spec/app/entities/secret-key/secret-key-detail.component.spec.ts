/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SecretKeyDetailComponent } from 'app/entities/secret-key/secret-key-detail.component';
import { SecretKey } from 'app/shared/model/secret-key.model';

describe('Component Tests', () => {
    describe('SecretKey Management Detail Component', () => {
        let comp: SecretKeyDetailComponent;
        let fixture: ComponentFixture<SecretKeyDetailComponent>;
        const route = ({ data: of({ secretKey: new SecretKey(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [SecretKeyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SecretKeyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SecretKeyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.secretKey).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
