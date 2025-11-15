import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirEnderecoComponent } from './inserir-endereco.component';

describe('InserirEnderecoComponent', () => {
  let component: InserirEnderecoComponent;
  let fixture: ComponentFixture<InserirEnderecoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InserirEnderecoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InserirEnderecoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
