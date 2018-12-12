/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SecretKeyUpdateComponent } from 'app/entities/secret-key/secret-key-update.component';
import { SecretKeyService } from 'app/entities/secret-key/secret-key.service';
import { SecretKey } from 'app/shared/model/secret-key.model';

describe('Component Tests', () => {
    describe('SecretKey Management Update Component', () => {
        let comp: SecretKeyUpdateComponent;
        let fixture: ComponentFixture<SecretKeyUpdateComponent>;
        let service: SecretKeyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [SecretKeyUpdateComponent]
            })
                .overrideTemplate(SecretKeyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SecretKeyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SecretKeyService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SecretKey(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.secretKey = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SecretKey();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.secretKey = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
