import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarPessoaComponent } from './listar-pessoa.component';

describe('ListarPessoaComponent', () => {
  let component: ListarPessoaComponent;
  let fixture: ComponentFixture<ListarPessoaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListarPessoaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
