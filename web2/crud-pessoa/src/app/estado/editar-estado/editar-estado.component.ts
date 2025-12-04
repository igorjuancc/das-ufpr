import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Estado } from '../../shared/models/estado.model';
import { EstadoService } from '../services/estado.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-estado',
  standalone: false,
  templateUrl: './editar-estado.component.html',
  styleUrl: './editar-estado.component.css'
})
export class EditarEstadoComponent {
  // Recebe uma referência do formulário aqui no componente
  // 'formEstado' deve ser o nome do formulário no HTML
  @ViewChild('formEstado') formEstado!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  estado: Estado = new Estado();

  mensagem: string = "";
  mensagem_detalhes: string = "";
  botaoDesabilitado = false;

  constructor(
    private estadoService: EstadoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    // Com o id, obtém o endereço
    /*
    const res = this.estadoService.buscarPorId(id);
    if (res !== undefined)
      this.estado = res;
    else
      throw new Error("Estado não encontrado: id = " + id);
    */
    this.estadoService.buscarPorId(+id).subscribe({
      next: (estado) => {
        if (estado == null) {
          this.mensagem = `Erro buscando estado ${this.estado.nome} - ${this.estado.sigla}`;
          this.mensagem_detalhes = `Estado não encontrada ${this.estado.nome} - ${this.estado.sigla}`;
          this.botaoDesabilitado = true;
        } else {
          this.estado = estado;
          this.botaoDesabilitado = false;
        }
      },
      error: (err) => {
        this.mensagem = `Erro buscando estado ${this.estado.nome} - ${this.estado.sigla}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        this.botaoDesabilitado = true;
      }
    });
  }

  /*
  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formEstado.form.valid) {
      // Efetivamente atualiza o estado
      this.estadoService.atualizar(this.estado);
      // Redireciona para /estados/listar
      this.router.navigate(['/estados']);
    }
  }
  */

  atualizar(): void {
    this.estadoService.atualizar(this.estado).subscribe({
      next: (estado) => {
        this.router.navigate(["/estados"]);
      },
      error: (err) => {
        this.mensagem = `Erro alterando estado ${this.estado.nome} - ${this.estado.sigla}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
  }
}
