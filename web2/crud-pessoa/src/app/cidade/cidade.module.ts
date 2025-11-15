import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditarCidadeComponent } from './editar-cidade';
import { ListarCidadeComponent } from './listar-cidade';
import { InserirCidadeComponent } from './inserir-cidade';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    EditarCidadeComponent,
    ListarCidadeComponent,
    InserirCidadeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class CidadeModule { }
