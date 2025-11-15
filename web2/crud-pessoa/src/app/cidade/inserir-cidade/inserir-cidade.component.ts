import { Component, ViewChild } from '@angular/core';
import { Cidade } from '../../shared';
import { CidadeService } from '../services';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-inserir-cidade',
  standalone: false,
  templateUrl: './inserir-cidade.component.html',
  styleUrl: './inserir-cidade.component.css'
})
export class InserirCidadeComponent {
  // Recebe uma referência do formulário aqui no componente
  // 'formCidade' deve ser o nome do formulário no HTML
  @ViewChild('formCidade') formCidade!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  cidade: Cidade = new Cidade();

  // Deve-se injetar no construtor:
  // - service, para efetuar a operação
  // - Router, para redirecionar para a tela de listagem depois da inserção
  constructor(
    private enderecoService: CidadeService,
    private router: Router) { }

  // Para inserir:
  // - Verifica se o formulário é válido, se não deu nenhum erro
  // - Se OK
  // . Chama o inserir do Service, this.pessoa está preenchida (binding)
  // . Redireciona para /cidades
  inserir(): void {
    if (this.formCidade.form.valid) {
      this.enderecoService.inserir(this.cidade);
      this.router.navigate(["/cidades"]);
    }
  }
}
