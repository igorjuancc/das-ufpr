import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditarCidadeComponent } from './editar-cidade';
import { ListarCidadeComponent } from './listar-cidade';
import { InserirCidadeComponent } from './inserir-cidade';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ModalCidadeComponent } from './modal-cidade/modal-cidade.component';



@NgModule({
  declarations: [
    EditarCidadeComponent,
    ListarCidadeComponent,
    InserirCidadeComponent,
    ModalCidadeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class CidadeModule { }
