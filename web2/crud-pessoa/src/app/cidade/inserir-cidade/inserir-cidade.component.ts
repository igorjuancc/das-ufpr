import { Component, OnInit, ViewChild } from '@angular/core';
import { Cidade, Estado } from '../../shared';
import { CidadeService } from '../services';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { EstadoService } from '../../estado/services';

@Component({
  selector: 'app-inserir-cidade',
  standalone: false,
  templateUrl: './inserir-cidade.component.html',
  styleUrl: './inserir-cidade.component.css'
})
export class InserirCidadeComponent implements OnInit {
  // Recebe uma referência do formulário aqui no componente
  // 'formCidade' deve ser o nome do formulário no HTML
  @ViewChild('formCidade') formCidade!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  cidade: Cidade = new Cidade();
  estados: Estado[] = [];
  mensagem: string = "";
  mensagem_detalhes: string = "";

  // Deve-se injetar no construtor:
  // - service, para efetuar a operação
  // - Router, para redirecionar para a tela de listagem depois da inserção
  constructor(
    private cidadeService: CidadeService,
    private estadoService: EstadoService, 
    private router: Router) { }

  ngOnInit(): void {
    this.estados = this.listarTodosEstados();
  }

  // Para inserir:
  // - Verifica se o formulário é válido, se não deu nenhum erro
  // - Se OK
  // . Chama o inserir do Service, this.pessoa está preenchida (binding)
  // . Redireciona para /cidades
  /*
  inserir(): void {
    if (this.formCidade.form.valid) {
      this.cidadeService.inserir(this.cidade);
      this.router.navigate(["/cidades"]);
    }
  }
  */

  inserir(): void {
    if (this.formCidade.form.valid) {
      this.cidadeService.inserir(this.cidade).subscribe({
        next: (cidade) => {
          this.router.navigate(["/cidades"]);
        },
        error: (err) => {
          this.mensagem = `Erro inserindo cidade ${this.cidade.nome} - ${this.cidade.estado?.nome}`;
          this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        }
      });
    }
  }

  listarTodosEstados(): Estado[] {
    this.estadoService.listarTodos().subscribe({
      next: (data: Estado[] | null) => {
        if (data == null) {
          this.estados = [];
        }
        else {
          this.estados = data;
        }
      },
      error: (err) => {
        this.mensagem = "Erro buscando lista de estados";
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
    return this.estados;
  }
}
