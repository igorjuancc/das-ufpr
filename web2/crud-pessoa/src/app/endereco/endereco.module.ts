import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InserirEnderecoComponent } from './inserir-endereco/inserir-endereco.component';
import { ListarEnderecoComponent } from './listar-endereco/listar-endereco.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EditarEnderecoComponent } from './editar-endereco/editar-endereco.component';



@NgModule({
  declarations: [
    InserirEnderecoComponent,
    ListarEnderecoComponent,
    EditarEnderecoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class EnderecoModule { }
