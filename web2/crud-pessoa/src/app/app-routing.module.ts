import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarPessoaComponent } from './pessoa/listar-pessoa';
import { InserirPessoaComponent } from './pessoa/inserir-pessoa';
import { EditarPessoaComponent } from './pessoa/editar-pessoa';
import { ListarEnderecoComponent } from './endereco/listar-endereco';
import { InserirEnderecoComponent } from './endereco/inserir-endereco';
import { EditarEnderecoComponent } from './endereco/editar-endereco';
import { ListarCidadeComponent } from './cidade/listar-cidade';
import { InserirCidadeComponent } from './cidade/inserir-cidade';
import { EditarCidadeComponent } from './cidade/editar-cidade';
import { ListarEstadoComponent } from './estado/listar-estado';
import { InserirEstadoComponent } from './estado/inserir-estado';
import { EditarEstadoComponent } from './estado/editar-estado';
import { LoginComponent } from './auth/login';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'pessoas',
    redirectTo: 'pessoas/listar'
  },
  {
    path: 'pessoas/listar',
    component: ListarPessoaComponent
  },
  {
    path: 'pessoas/novo',
    component: InserirPessoaComponent
  },
  {
    path: 'pessoas/editar/:id',
    component: EditarPessoaComponent
  },
  {
    path: 'enderecos',
    redirectTo: 'enderecos/listar'
  },
  {
    path: 'enderecos/listar',
    component: ListarEnderecoComponent
  },
  {
    path: 'enderecos/novo',
    component: InserirEnderecoComponent
  },
  {
    path: 'enderecos/editar/:id',
    component: EditarEnderecoComponent
  },
  {
    path: 'cidades',
    redirectTo: 'cidades/listar'
  },
  {
    path: 'cidades/listar',
    component: ListarCidadeComponent
  },
  {
    path: 'cidades/novo',
    component: InserirCidadeComponent
  },
  {
    path: 'cidades/editar/:id',
    component: EditarCidadeComponent
  }
  ,
  {
    path: 'estados',
    redirectTo: 'estados/listar'
  },
  {
    path: 'estados/listar',
    component: ListarEstadoComponent
  },
  {
    path: 'estados/novo',
    component: InserirEstadoComponent
  },
  {
    path: 'estados/editar/:id',
    component: EditarEstadoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
