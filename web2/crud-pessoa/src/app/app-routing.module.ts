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
import { authGuard } from './auth/auth.guard';

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
    component: ListarPessoaComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,GERENTE,FUNC'
    }
  },
  {
    path: 'pessoas/novo',
    component: InserirPessoaComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,GERENTE,FUNC'
    }
  },
  {
    path: 'pessoas/editar/:id',
    component: EditarPessoaComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,GERENTE,FUNC'
    }
  },
  {
    path: 'enderecos',
    redirectTo: 'enderecos/listar'
  },
  {
    path: 'enderecos/listar',
    component: ListarEnderecoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,GERENTE'
    }
  },
  {
    path: 'enderecos/novo',
    component: InserirEnderecoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,GERENTE'
    }
  },
  {
    path: 'enderecos/editar/:id',
    component: EditarEnderecoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,GERENTE'
    }
  },
  {
    path: 'cidades',
    redirectTo: 'cidades/listar'
  },
  {
    path: 'cidades/listar',
    component: ListarCidadeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'cidades/novo',
    component: InserirCidadeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'cidades/editar/:id',
    component: EditarCidadeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  }
  ,
  {
    path: 'estados',
    redirectTo: 'estados/listar'
  },
  {
    path: 'estados/listar',
    component: ListarEstadoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,FUNC'
    }
  },
  {
    path: 'estados/novo',
    component: InserirEstadoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,FUNC'
    }
  },
  {
    path: 'estados/editar/:id',
    component: EditarEstadoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN,FUNC'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
