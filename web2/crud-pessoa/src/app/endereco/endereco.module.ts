import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InserirEnderecoComponent } from './inserir-endereco';
import { ListarEnderecoComponent } from './listar-endereco';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EditarEnderecoComponent } from './editar-endereco';
import { ModalEnderecoComponent } from './modal-endereco/modal-endereco.component';



@NgModule({
  declarations: [
    InserirEnderecoComponent,
    ListarEnderecoComponent,
    EditarEnderecoComponent,
    ModalEnderecoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class EnderecoModule { }
