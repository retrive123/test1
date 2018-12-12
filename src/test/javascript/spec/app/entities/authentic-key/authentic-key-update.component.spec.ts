/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AuthenticKeyUpdateComponent } from 'app/entities/authentic-key/authentic-key-update.component';
import { AuthenticKeyService } from 'app/entities/authentic-key/authentic-key.service';
import { AuthenticKey } from 'app/shared/model/authentic-key.model';

describe('Component Tests', () => {
    describe('AuthenticKey Management Update Component', () => {
        let comp: AuthenticKeyUpdateComponent;
        let fixture: ComponentFixture<AuthenticKeyUpdateComponent>;
        let service: AuthenticKeyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AuthenticKeyUpdateComponent]
            })
                .overrideTemplate(AuthenticKeyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AuthenticKeyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AuthenticKeyService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AuthenticKey(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.authenticKey = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AuthenticKey();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.authenticKey = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
