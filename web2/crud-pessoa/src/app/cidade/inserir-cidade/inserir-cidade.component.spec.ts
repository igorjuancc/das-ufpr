import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirCidadeComponent } from './inserir-cidade.component';

describe('InserirCidadeComponent', () => {
  let component: InserirCidadeComponent;
  let fixture: ComponentFixture<InserirCidadeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InserirCidadeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InserirCidadeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
