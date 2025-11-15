import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarEstadoComponent } from './listar-estado.component';

describe('ListarEstadoComponent', () => {
  let component: ListarEstadoComponent;
  let fixture: ComponentFixture<ListarEstadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListarEstadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarEstadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
