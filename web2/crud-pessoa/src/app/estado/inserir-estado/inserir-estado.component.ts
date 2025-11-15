import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Estado } from '../../shared/models/estado.model';
import { EstadoService } from '../services/estado.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-estado',
  standalone: false,
  templateUrl: './inserir-estado.component.html',
  styleUrl: './inserir-estado.component.css'
})
export class InserirEstadoComponent {
  // Recebe uma referência do formulário aqui no componente
  // 'formEstado' deve ser o nome do formulário no HTML
  @ViewChild('formEstado') formEstado!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  estado: Estado = new Estado();

  // Deve-se injetar no construtor:
  // - service, para efetuar a operação
  // - Router, para redirecionar para a tela de listagem depois da inserção
  constructor(
    private estadoService: EstadoService,
    private router: Router) { }

  // Para inserir:
  // - Verifica se o formulário é válido, se não deu nenhum erro
  // - Se OK
  // . Chama o inserir do Service, this.pessoa está preenchida (binding)
  // . Redireciona para /pessoas
  inserir(): void {
    if (this.formEstado.form.valid) {
      this.estadoService.inserir(this.estado);
      this.router.navigate(["/estados"]);
    }
  }
}
