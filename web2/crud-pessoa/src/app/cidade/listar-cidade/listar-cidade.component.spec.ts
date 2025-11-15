import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCidadeComponent } from './listar-cidade.component';

describe('ListarCidadeComponent', () => {
  let component: ListarCidadeComponent;
  let fixture: ComponentFixture<ListarCidadeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListarCidadeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarCidadeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
