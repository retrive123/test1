/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AuthenticKeyDeleteDialogComponent } from 'app/entities/authentic-key/authentic-key-delete-dialog.component';
import { AuthenticKeyService } from 'app/entities/authentic-key/authentic-key.service';

describe('Component Tests', () => {
    describe('AuthenticKey Management Delete Component', () => {
        let comp: AuthenticKeyDeleteDialogComponent;
        let fixture: ComponentFixture<AuthenticKeyDeleteDialogComponent>;
        let service: AuthenticKeyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AuthenticKeyDeleteDialogComponent]
            })
                .overrideTemplate(AuthenticKeyDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AuthenticKeyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AuthenticKeyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
