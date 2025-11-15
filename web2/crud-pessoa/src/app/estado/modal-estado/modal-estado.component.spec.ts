import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEstadoComponent } from './modal-estado.component';

describe('ModalEstadoComponent', () => {
  let component: ModalEstadoComponent;
  let fixture: ComponentFixture<ModalEstadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModalEstadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalEstadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
