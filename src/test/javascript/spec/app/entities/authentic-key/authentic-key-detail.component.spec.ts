/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AuthenticKeyDetailComponent } from 'app/entities/authentic-key/authentic-key-detail.component';
import { AuthenticKey } from 'app/shared/model/authentic-key.model';

describe('Component Tests', () => {
    describe('AuthenticKey Management Detail Component', () => {
        let comp: AuthenticKeyDetailComponent;
        let fixture: ComponentFixture<AuthenticKeyDetailComponent>;
        const route = ({ data: of({ authenticKey: new AuthenticKey(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AuthenticKeyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AuthenticKeyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AuthenticKeyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.authenticKey).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
