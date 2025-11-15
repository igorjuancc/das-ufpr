import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCidadeComponent } from './modal-cidade.component';

describe('ModalCidadeComponent', () => {
  let component: ModalCidadeComponent;
  let fixture: ComponentFixture<ModalCidadeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModalCidadeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalCidadeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
