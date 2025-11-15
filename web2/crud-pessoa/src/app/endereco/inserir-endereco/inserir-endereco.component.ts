import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Endereco } from '../../shared';
import { EnderecoService } from '../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-endereco',
  standalone: false,
  templateUrl: './inserir-endereco.component.html',
  styleUrl: './inserir-endereco.component.css'
})
export class InserirEnderecoComponent {
  // Recebe uma referência do formulário aqui no componente
  // 'formPessoa' deve ser o nome do formulário no HTML
  @ViewChild('formEndereco') formEndereco!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  endereco: Endereco = new Endereco();

  // Deve-se injetar no construtor:
  // - service, para efetuar a operação
  // - Router, para redirecionar para a tela de listagem depois da inserção
  constructor(
    private enderecoService: EnderecoService,
    private router: Router) { }

  // Para inserir:
  // - Verifica se o formulário é válido, se não deu nenhum erro
  // - Se OK
  // . Chama o inserir do Service, this.pessoa está preenchida (binding)
  // . Redireciona para /pessoas
  inserir(): void {
    if (this.formEndereco.form.valid) {
      this.enderecoService.inserir(this.endereco);
      this.router.navigate(["/enderecos"]);
    }
  }
}
