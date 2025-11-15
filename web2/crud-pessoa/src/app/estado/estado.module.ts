import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InserirEstadoComponent } from './inserir-estado';
import { EditarEstadoComponent } from './editar-estado';
import { ListarEstadoComponent } from './listar-estado';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    InserirEstadoComponent,
    EditarEstadoComponent,
    ListarEstadoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class EstadoModule { }
