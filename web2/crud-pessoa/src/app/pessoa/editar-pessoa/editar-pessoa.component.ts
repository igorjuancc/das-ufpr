import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pessoa } from '../../shared';
import { PessoaService } from '../services';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-pessoa',
  standalone: false,
  templateUrl: './editar-pessoa.component.html',
  styleUrl: './editar-pessoa.component.css'
})
export class EditarPessoaComponent implements OnInit {
  // Recebe uma referência do formulário aqui no componente
  // 'formPessoa' deve ser o nome do formulário no HTML
  @ViewChild('formPessoa') formPessoa!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  pessoa: Pessoa = new Pessoa();

  mensagem: string = "";
  mensagem_detalhes: string = "";
  botaoDesabilitado = false;

  constructor(
    private pessoaService: PessoaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    // Com o id, obtém a pessoa
    /*
    const res = this.pessoaService.buscarPorId(id);
    if (res !== undefined)
      this.pessoa = res;
    else
      throw new Error("Pessoa não encontrada: id = " + id);
    */

    this.pessoaService.buscarPorId(+id).subscribe({
      next: (pessoa) => {
        if (pessoa == null) {
          this.mensagem = `Erro buscando pessoa ${id}`;
          this.mensagem_detalhes = `Pessoa não encontrada ${id}`;
          this.botaoDesabilitado = true;
        } else {
          this.pessoa = pessoa;
          this.botaoDesabilitado = false;
        }
      },
      error: (err) => {
        this.mensagem = `Erro buscando pessoa ${id}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        this.botaoDesabilitado = true;
      }
    });
  }

  /*
  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formPessoa.form.valid) {
      // Efetivamente atualiza a pessoa
      this.pessoaService.atualizar(this.pessoa);
      // Redireciona para /pessoas/listar
      this.router.navigate(['/pessoas']);
    }
  }
  */

  atualizar(): void {
    this.pessoaService.atualizar(this.pessoa).subscribe({
      next: (pessoa) => {
        this.router.navigate(["/pessoas"]);
      },
      error: (err) => {
        this.mensagem = `Erro alterando pessoa ${this.pessoa.nome}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
  }

}
