import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { PessoaService } from './services';
import { ListarPessoaComponent } from './listar-pessoa';
import { InserirPessoaComponent } from './inserir-pessoa';
import { EditarPessoaComponent } from './editar-pessoa';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { SharedModule } from '../shared';


@NgModule({
  declarations: [
    ListarPessoaComponent,
    InserirPessoaComponent,
    EditarPessoaComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NgxMaskDirective,
    NgxMaskPipe,
    SharedModule
  ],
  providers: [
    PessoaService,
    provideNgxMask()
  ]
})
export class PessoaModule { }
