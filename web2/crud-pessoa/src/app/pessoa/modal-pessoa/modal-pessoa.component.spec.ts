import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalPessoaComponent } from './modal-pessoa.component';

describe('ModalPessoaComponent', () => {
  let component: ModalPessoaComponent;
  let fixture: ComponentFixture<ModalPessoaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModalPessoaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
